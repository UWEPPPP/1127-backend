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
     * create weid
     * 生成WeId及其公私钥
     *
     * @return {@link AccountModel}
     * @throws WeIdentityException weidentity exception
     */
    AccountModel createWeId() throws WeIdentityException;

    /**
     * create weid
     *
     * @param publicKey  public key
     * @param privateKey private key
     * @return {@link AccountModel}
     */
    String createWeId(String publicKey, String privateKey);


    /**
     * register authority issuer
     * 注册权威机构
     *
     * @param issuerWeId    被授权方WeId
     * @param authorityName 名字
     * @return {@link Boolean}
     * @throws WeIdentityException weidentity exception
     */
    Boolean registerAuthorityIssuer(String issuerWeId, String authorityName) throws WeIdentityException;

    /**
     * is authority issuer
     * 是否为权威机构
     *
     * @param issuerWeId weid
     * @return {@link Boolean}
     * @throws WeIdentityException weidentity exception
     */
    Boolean isAuthorityIssuer(String issuerWeId) throws WeIdentityException;

    /**
     * register cpt
     * 注册CPT
     *
     * @param publisher  发行者
     * @param privateKey 私钥
     * @param claim      CPT数据类型定义 必须为json格式 可自定义或者提供几个模板？
     * @return {@link CptBaseInfo}
     * @throws WeIdentityException weidentity exception
     */
    CptBaseInfo registerCpt(String publisher, String privateKey, Map<String, Object> claim) throws WeIdentityException;


    /**
     * create credential
     *
     * @param cptId          cpt id
     * @param issuer         issuer
     * @param privateKey     private key
     * @param expirationDate expiration date
     * @param claimDate      claim date
     * @return {@link CredentialPojo}
     * @throws WeIdentityException weidentity exception
     */
    CredentialPojo createCredential(Integer cptId, String issuer, String privateKey, Long expirationDate, Map<String, Object> claimDate) throws WeIdentityException;
}
