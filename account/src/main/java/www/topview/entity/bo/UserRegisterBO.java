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

    public UserRegisterBO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
