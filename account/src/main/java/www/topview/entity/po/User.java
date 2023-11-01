package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 总用户表
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    private Integer id;
    private String username;
    private String password;
    @TableField("weid")
    private String weId;
    private String address;
    private String publicKey;
    private String privateKey;
}
