package www.topview.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * register worker bo
 *
 * @author 刘家辉
 * @date 2023/11/03
 */
@Data
@Accessors(chain = true)
public class AddWorkerBO {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "组名不能为空")
    private String groupName;
}
