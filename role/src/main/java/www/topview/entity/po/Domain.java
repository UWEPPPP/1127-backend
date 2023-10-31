package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * domain
 *
 * @author 刘家辉
 * @date 2023/10/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("admin")
public class Domain {
    private Integer id;
    private String domainName;
    private String domainAddress;
    private Integer domainAdminId;
}
