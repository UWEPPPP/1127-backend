package www.topview.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * apply
 *
 * @author 刘家辉
 * @date 2023/10/30
 */
@Data
@TableName("application_user")
@Accessors(chain = true)
public class Apply {
    private Integer id;
    @TableField("company_id")
    private String companyId;
    private Integer status;
    @TableField("applicant_username")
    private String userName;
    @TableField("applicant_password")
    private String userPassword;
    private String payload;
}
