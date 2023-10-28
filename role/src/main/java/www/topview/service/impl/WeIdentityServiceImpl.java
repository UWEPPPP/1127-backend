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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
@Service
@Slf4j
public class WeIdentityServiceImpl implements www.topview.service.WeIdentityService {
    @Value("${weidentity.admin_private_key}")
    private String adminPrivateKeyPath;

    private final AuthorityIssuerService authorityIssuerService = new AuthorityIssuerServiceImpl();

    private final CptService cptService = new CptServiceImpl();

    private final CredentialPojoService credentialService = new CredentialPojoServiceImpl();

    private final WeIdService weIdService = new WeIdServiceImpl();




    /**
     * 生成WeId及其公私钥
     * @return {@link CreateWeIdDataResult}
     */
    @Override
    public CreateWeIdDataResult createWeId(){
        ResponseData<CreateWeIdDataResult> weId = weIdService.createWeId();
        if (weId.getErrorCode() == ErrorCode.SUCCESS.getCode()){
            log.info("create weId success");
            return weId.getResult();
        }
        log.error("create weId error");
        return null;
    }

    /**
     * 注册权威机构
     * @param issuerWeId 被授权方WeId
     * @param authorityName 名字
     * @return {@link Boolean}
     */
    @Override
    public  Boolean registerAuthorityIssuer(String issuerWeId, String authorityName){
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
        log.info(
                "recognizeAuthorityIssuer is result,errorCode:{},errorMessage:{}",
                booleanResponseData.getErrorCode(),
                booleanResponseData.getErrorMessage()
        );
        return booleanResponseData.getResult();
    }

    /**
     * 是否为权威机构
     * @param issuerWeId weid
     * @return {@link Boolean}
     */
    @Override
    public Boolean isAuthorityIssuer(String issuerWeId){
        ResponseData<Boolean> booleanResponseData = authorityIssuerService.isAuthorityIssuer(issuerWeId);
        log.info(
                "recognizeAuthorityIssuer is result,errorCode:{},errorMessage:{}",
                booleanResponseData.getErrorCode(),
                booleanResponseData.getErrorMessage()
        );
        return booleanResponseData.getResult();
    }

    /**
     * @param publisher 发行者
     * @param privateKey 私钥
     * @param claim CPT数据类型定义 必须为json格式 可自定义或者提供几个模板？
     * @return {@link CptBaseInfo}
     */
    @Override
    public CptBaseInfo registerCpt(String publisher, String privateKey, Map<String, Object> claim){

        CptMapArgs cptMapArgs = new CptMapArgs();
        WeIdAuthentication weIdAuthentication = new WeIdAuthentication();
        weIdAuthentication.setWeId(publisher);
        weIdAuthentication.setWeIdPrivateKey(new WeIdPrivateKey());
        weIdAuthentication.getWeIdPrivateKey().setPrivateKey(privateKey);
        cptMapArgs.setWeIdAuthentication(weIdAuthentication);
        cptMapArgs.setCptJsonSchema(claim);
        ResponseData<CptBaseInfo> cptBaseInfoResponseData = cptService.registerCpt(cptMapArgs);
        log.info(
                "registerCpt is result,errorCode:{},errorMessage:{}",
                cptBaseInfoResponseData.getErrorCode(),
                cptBaseInfoResponseData.getErrorMessage()
        );
        return cptBaseInfoResponseData.getResult();
    }

    @Override
    public CredentialPojo createCredential(Integer cptId, String issuer, String privateKey, Long expirationDate, Map<String, Object> claimDate) {

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
        log.info(
                "createCredential is result,errorCode:{},errorMessage:{}",
                response.getErrorCode(),
                response.getErrorMessage()
        );
        return response.getResult();
    }


    private String adminKey(){
        FileReader privateKey = new FileReader(adminPrivateKeyPath);
        return privateKey.readString();
    }


}
