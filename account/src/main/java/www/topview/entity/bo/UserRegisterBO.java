package www.topview.entity.bo;

import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/10/27
 */

@Data
public class UserRegisterBO {


    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * 员工属于公司的证明材料
     */
    private String payload;
    public UserRegisterBO() {
    }

    public UserRegisterBO(String username, String password, Integer companyId, String payload) {
        this.username = username;
        this.password = password;
        this.companyId = companyId;
        this.payload = payload;
    }
}
