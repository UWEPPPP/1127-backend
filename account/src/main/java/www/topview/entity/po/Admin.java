package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * admin
 *
 * @author 刘家辉
 * @date 2023/10/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("admin")
public class Admin {
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
    @TableField("weid")
    private String weId;

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
     * 如果companyId为-1 则为域管理员
     * company id
     */
    private Integer companyId;
    /**
     * domain id
     */
    private Integer domainId;
}
