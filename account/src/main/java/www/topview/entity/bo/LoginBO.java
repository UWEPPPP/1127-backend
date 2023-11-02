package www.topview.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Lictory
 * @date : 2023/10/30
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginBO {

    /**
     * 账户名
     */
    private String username;


    /**
     * 密码
     */
    private String password;

    /**
     * role 0 普通公司员工 1 公司管理员 2 域管理员
     */
    private Integer role;
}
