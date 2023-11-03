package www.topview.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * add company dto
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@Data
@Accessors(chain = true)
public class AddCompanyDTO {
    @Min(value = 1, message = "passerId 必须为正整数")
    private Integer passer;
    @NotNull(message = "用户名不能为空")
    private String founderName;
    @NotNull(message = "密码不能为空")
    private String founderPassword;
    @NotNull(message = "公司名不能为空")
    private String companyName;
    @Min(value = 1, message = "domainId 必须为正整数")
    private Integer domainId;

}
