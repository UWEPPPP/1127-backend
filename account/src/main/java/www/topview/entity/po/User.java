package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/10/29
 */

@Data
@TableName("'user'")
public class User {

    /**
     * 用户主键
     */
    private String id;

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
    private String weIdUser;

    /**
     * 用户公钥
     */
    private String publicKey;


    /**
     * 用户私钥
     */
    private String privateKey;


    public User(String id, String username, String password, String weIdUser, String publicKey, String privateKey) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.weIdUser = weIdUser;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
}
