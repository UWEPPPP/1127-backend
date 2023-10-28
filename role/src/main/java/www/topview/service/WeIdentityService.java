package www.topview.service;

import com.webank.weid.protocol.base.CptBaseInfo;
import com.webank.weid.protocol.base.CredentialPojo;
import com.webank.weid.protocol.response.CreateWeIdDataResult;

import java.util.Map;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
public interface WeIdentityService {
    /**
     * 生成WeId及其公私钥
     *
     * @return {@link CreateWeIdDataResult}
     */
    CreateWeIdDataResult createWeId();

    /**
     * 注册权威机构
     *
     * @param issuerWeId    被授权方WeId
     * @param authorityName 名字
     * @return {@link Boolean}
     */
    Boolean registerAuthorityIssuer(String issuerWeId, String authorityName);

    /**
     * 是否为权威机构
     *
     * @param issuerWeId weid
     * @return {@link Boolean}
     */
    Boolean isAuthorityIssuer(String issuerWeId);

    /**
     * 注册CPT
     * @param publisher  发行者
     * @param privateKey 私钥
     * @param claim      CPT数据类型定义 必须为json格式 可自定义或者提供几个模板？
     * @return {@link CptBaseInfo}
     */
    CptBaseInfo registerCpt(String publisher, String privateKey, Map<String, Object> claim);


    /**
     * @param cptId
     * @param issuer
     * @param privateKey
     * @param expirationDate
     * @param claimDate
     * @return {@link CredentialPojo}
     */
    CredentialPojo createCredential(Integer cptId, String issuer, String privateKey, Long expirationDate, Map<String, Object> claimDate);
}
