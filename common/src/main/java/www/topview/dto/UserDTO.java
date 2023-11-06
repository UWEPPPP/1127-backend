package www.topview.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户信息dto
 *
 * @author lql
 * @date 2023/11/06
 */
@Data
@Accessors(chain = true)
public class UserDTO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * weid
     */
    private String weId;

    /**
     * 地址
     */
    private String address;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 角色
     */
    private Integer role;
}
