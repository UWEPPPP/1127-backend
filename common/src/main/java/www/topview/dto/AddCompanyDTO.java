package www.topview.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * add company dto
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@Data
@Accessors(chain = true)
public class AddCompanyDTO {
    private Integer passer;
    private String founderName;
    private String founderPassword;
    private String companyName;
    private Integer domainId;

}
