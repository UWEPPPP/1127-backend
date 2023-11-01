package www.topview.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * add company dto
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("domain_admin_info")
@Accessors(chain = true)
public class AddCompanyDTO {
    private Integer passer;
    private String founderName;
    private String founderPassword;
    private String companyName;
    private Integer domainId;

}
