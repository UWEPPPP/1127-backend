package www.topview.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * add worker bo
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("domain_admin_info")
@Accessors(chain = true)
public class AddWorkerDTO {
    private Integer passer;
    private String username;
    private String password;
    private Integer companyId;
    private Integer domainId;
    private String groupName;
}
