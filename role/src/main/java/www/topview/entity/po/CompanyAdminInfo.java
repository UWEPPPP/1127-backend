package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * admin
 *
 * @author 刘家辉
 * @date 2023/10/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("company_admin_info")

@Accessors(chain = true)
public class CompanyAdminInfo {
    /**
     * 用户主键
     */
    @TableId
    private Integer id;

    /**
     * 用户的weId
     */
    @TableField("weid")
    private String weId;

    /**
     * 如果companyId为-1域管理员
     * company id
     */
    private Integer companyId;
    /**
     * domain id
     */
    private Integer domainId;
}
