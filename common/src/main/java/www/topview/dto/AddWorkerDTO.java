package www.topview.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * add worker bo
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@Data
@Accessors(chain = true)
public class AddWorkerDTO {
    @Min(value = 1, message = "passerId 必须为正整数")
    private Integer passer;
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    @Min(value = 1, message = "companyId 必须为正整数")
    private Integer companyId;
    @Min(value = 1, message = "domainId 必须为正整数")
    private Integer domainId;
    @NotNull(message = "组名不能为空")
    private String groupName;
}
