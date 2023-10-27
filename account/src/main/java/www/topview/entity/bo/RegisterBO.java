package www.topview.entity.bo;

import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/10/27
 */

@Data
public class RegisterBO {

    /**
     * weId
     */
    private String weId;

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


}
