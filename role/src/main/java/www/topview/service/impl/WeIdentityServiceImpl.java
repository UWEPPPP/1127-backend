package www.topview.service.impl;

import cn.hutool.core.io.file.FileReader;
import com.webank.weid.blockchain.protocol.response.ResponseData;
import com.webank.weid.constant.CredentialType;
import com.webank.weid.constant.ErrorCode;
import com.webank.weid.protocol.base.*;
import com.webank.weid.protocol.request.CptMapArgs;
import com.webank.weid.protocol.request.CreateCredentialPojoArgs;
import com.webank.weid.protocol.request.RegisterAuthorityIssuerArgs;
import com.webank.weid.protocol.response.CreateWeIdDataResult;
import com.webank.weid.service.impl.AuthorityIssuerServiceImpl;
import com.webank.weid.service.impl.CptServiceImpl;
import com.webank.weid.service.impl.CredentialPojoServiceImpl;
import com.webank.weid.service.impl.WeIdServiceImpl;
import com.webank.weid.service.rpc.AuthorityIssuerService;
import com.webank.weid.service.rpc.CptService;
import com.webank.weid.service.rpc.CredentialPojoService;
import com.webank.weid.service.rpc.WeIdService;
import com.webank.weid.util.DataToolUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.crypto.keypair.ECDSAKeyPair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import www.topview.entity.model.AccountModel;
import www.topview.exception.WeIdentityException;

import java.math.BigInteger;
import java.util.Map;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
@Service
@Slf4j
public class WeIdentityServiceImpl implements www.topview.service.WeIdentityService {
    @Value("${weIdentity.admin_private_key}")
    private String adminPrivateKeyPath;


    private final AuthorityIssuerService authorityIssuerService = new AuthorityIssuerServiceImpl();

    private final CptService cptService = new CptServiceImpl();

    private final CredentialPojoService credentialService = new CredentialPojoServiceImpl();

    private final WeIdService weIdService = new WeIdServiceImpl();


    /**
     * 生成WeId及其公私钥
     *
     * @return {@link CreateWeIdDataResult}
     */
    @Override
    public AccountModel createWeId() throws WeIdentityException {
        ResponseData<CreateWeIdDataResult> weId = weIdService.createWeId();
        ECDSAKeyPair ecdsaKeyPair = new ECDSAKeyPair();
        if (weId.getErrorCode() == ErrorCode.SUCCESS.getCode()) {
            String address = ecdsaKeyPair.getAddress(DataToolUtils.addressFromPublic(new BigInteger( weId.getResult().getUserWeIdPublicKey().getPublicKey())));
            AccountModel accountModel = new AccountModel();

            accountModel.setAccountAddress(address)
                    .setWeId(weId.getResult().getWeId())
                    .setPublicKey(weId.getResult().getUserWeIdPublicKey().getPublicKey())
                    .setPrivateKey(weId.getResult().getUserWeIdPrivateKey().getPrivateKey());
            log.info("create weId success");
            return accountModel;
        }
        throw new WeIdentityException("createWeId 异常");
    }

    /**
     * 注册权威机构
     *
     * @param issuerWeId    被授权方WeId
     * @param authorityName 名字
     * @return {@link Boolean}
     */
    @Override
    public Boolean registerAuthorityIssuer(String issuerWeId, String authorityName) throws WeIdentityException {
        AuthorityIssuer authorityIssuer = new AuthorityIssuer();
        authorityIssuer.setWeId(issuerWeId);
        authorityIssuer.setName(authorityName);
        //AccValue 授权方累计判断值
        authorityIssuer.setAccValue("0");
        String key = adminKey();
        RegisterAuthorityIssuerArgs registerAuthorityIssuerArgs = new RegisterAuthorityIssuerArgs();
        registerAuthorityIssuerArgs.setAuthorityIssuer(authorityIssuer);
        registerAuthorityIssuerArgs.setWeIdPrivateKey(new WeIdPrivateKey());
        registerAuthorityIssuerArgs.getWeIdPrivateKey().setPrivateKey(key);
        ResponseData<Boolean> booleanResponseData = authorityIssuerService.registerAuthorityIssuer(registerAuthorityIssuerArgs);
        if (booleanResponseData.getErrorCode() == ErrorCode.SUCCESS.getCode()) {
            log.info("registerAuthorityIssuer success");
            return booleanResponseData.getResult();
        }
        throw new WeIdentityException("registerAuthorityIssuer 异常");
    }

    /**
     * 是否为权威机构
     *
     * @param issuerWeId weid
     * @return {@link Boolean}
     */
    @Override
    public Boolean isAuthorityIssuer(String issuerWeId) throws WeIdentityException {
        ResponseData<Boolean> booleanResponseData = authorityIssuerService.isAuthorityIssuer(issuerWeId);
        if (booleanResponseData.getErrorCode() == ErrorCode.SUCCESS.getCode()) {
            log.info("isAuthorityIssuer success");
            return booleanResponseData.getResult();
        }
        throw new WeIdentityException("isAuthorityIssuer 异常");
    }

    /**
     * @param publisher  发行者
     * @param privateKey 私钥
     * @param claim      CPT数据类型定义 必须为json格式 可自定义或者提供几个模板？
     * @return {@link CptBaseInfo}
     */
    @Override
    public CptBaseInfo registerCpt(String publisher, String privateKey, Map<String, Object> claim) throws WeIdentityException {

        CptMapArgs cptMapArgs = new CptMapArgs();
        WeIdAuthentication weIdAuthentication = new WeIdAuthentication();
        weIdAuthentication.setWeId(publisher);
        weIdAuthentication.setWeIdPrivateKey(new WeIdPrivateKey());
        weIdAuthentication.getWeIdPrivateKey().setPrivateKey(privateKey);
        cptMapArgs.setWeIdAuthentication(weIdAuthentication);
        cptMapArgs.setCptJsonSchema(claim);
        ResponseData<CptBaseInfo> cptBaseInfoResponseData = cptService.registerCpt(cptMapArgs);
        if (cptBaseInfoResponseData.getErrorCode() == ErrorCode.SUCCESS.getCode()) {
            log.info("registerCpt success");
            return cptBaseInfoResponseData.getResult();
        }
        throw new WeIdentityException("registerCpt 异常");
    }

    @Override
    public CredentialPojo createCredential(Integer cptId, String issuer, String privateKey, Long expirationDate, Map<String, Object> claimDate) throws WeIdentityException {

        CreateCredentialPojoArgs<Map<String, Object>> args = new CreateCredentialPojoArgs<>();
        args.setCptId(cptId);
        args.setIssuer(issuer);
        args.setType(CredentialType.ORIGINAL);
        args.setClaim(claimDate);
        args.setExpirationDate(System.currentTimeMillis() + expirationDate);

        WeIdAuthentication weIdAuthentication = new WeIdAuthentication();
        weIdAuthentication.setWeIdPrivateKey(new WeIdPrivateKey());
        weIdAuthentication.getWeIdPrivateKey().setPrivateKey(privateKey);
        weIdAuthentication.setWeId(issuer);
        weIdAuthentication.setAuthenticationMethodId(issuer);
        args.setWeIdAuthentication(weIdAuthentication);

        ResponseData<CredentialPojo> response =
                credentialService.createCredential(args);
        if (response.getErrorCode() == ErrorCode.SUCCESS.getCode()) {
            log.info("createCredential success");
            return response.getResult();
        }
        throw new WeIdentityException("createCredential 异常");
    }


    private String adminKey() {
        FileReader privateKey = new FileReader(adminPrivateKeyPath);
        return privateKey.readString();
    }


}
