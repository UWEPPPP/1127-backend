package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * domain admin info
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("domain_admin_info")
@Accessors(chain = true)
public class DomainAdminInfo {
    private Integer id;
    @TableId("weid")
    private String weId;
    private Integer domainId;
}
