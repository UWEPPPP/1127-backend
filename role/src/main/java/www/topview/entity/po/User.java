package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Lictory
 * @date : 2023/10/29
 */

@Data
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
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
     * group name
     */
    private String groupName;
}
