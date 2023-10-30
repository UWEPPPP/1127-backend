package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/10/29
 */

@Data
@TableName("user")
public class User {

    /**
     * 用户主键
     */
    @TableId
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
     * 用户的weId
     */
    @TableField("weid_user")
    private String weIdUser;

    /**
     * 用户公钥
     */
    @TableField("public_key")
    private String publicKey;


    /**
     * 用户私钥
     */
    @TableField("private_key")
    private String privateKey;

    /**
     * 账户地址
     */
    private String address;

    /**
     * 0为企业管理员      1为企业员工
     */
    private Integer role;

    public User() {
    }

    public User(Integer id, String username, String password, String weIdUser, String publicKey, String privateKey, String address, Integer role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.weIdUser = weIdUser;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.address = address;
        this.role = role;
    }
}
