package www.topview.entity.bo;

import com.webank.weid.protocol.base.Credential;
import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/10/29
 */

@Data
public class CompanyRegisterBO {

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 公司的全局唯一Id
     */
    private String companyId;

    /**
     * 公司注册人的weId
     */
    private String weId;


    /**
     * 公司用于注册的证明证书
     */
    private Credential credential;
}
