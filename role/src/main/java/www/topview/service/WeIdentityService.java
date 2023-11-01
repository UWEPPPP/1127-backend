package www.topview.service;

import com.webank.weid.protocol.base.CptBaseInfo;
import com.webank.weid.protocol.base.CredentialPojo;
import www.topview.entity.model.AccountModel;
import www.topview.exception.WeIdentityException;

import java.util.Map;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
public interface WeIdentityService {
    /**
     * create we id
     * 生成WeId及其公私钥
     *
     * @return {@link AccountModel}
     * @throws WeIdentityException we identity exception
     */
    AccountModel createWeId() throws WeIdentityException;

    /**
     * create we id
     *
     * @param publicKey  public key
     * @param privateKey private key
     * @return {@link AccountModel}
     * @throws WeIdentityException we identity exception
     */
    String createWeId(String publicKey, String privateKey) throws WeIdentityException;


    /**
     * register authority issuer
     * 注册权威机构
     *
     * @param issuerWeId    被授权方WeId
     * @param authorityName 名字
     * @return {@link Boolean}
     * @throws WeIdentityException we identity exception
     */
    Boolean registerAuthorityIssuer(String issuerWeId, String authorityName) throws WeIdentityException;

    /**
     * 是否为权威机构
     *
     * @param issuerWeId weid
     * @return {@link Boolean}
     */
    Boolean isAuthorityIssuer(String issuerWeId) throws WeIdentityException;

    /**
     * 注册CPT
     *
     * @param publisher  发行者
     * @param privateKey 私钥
     * @param claim      CPT数据类型定义 必须为json格式 可自定义或者提供几个模板？
     * @return {@link CptBaseInfo}
     */
    CptBaseInfo registerCpt(String publisher, String privateKey, Map<String, Object> claim) throws WeIdentityException;


    /**
     * @param cptId
     * @param issuer
     * @param privateKey
     * @param expirationDate
     * @param claimDate
     * @return {@link CredentialPojo}
     */
    CredentialPojo createCredential(Integer cptId, String issuer, String privateKey, Long expirationDate, Map<String, Object> claimDate) throws WeIdentityException;
}
