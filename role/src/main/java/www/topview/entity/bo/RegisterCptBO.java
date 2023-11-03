package www.topview.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
@Data
public class RegisterCptBO {
    @NotNull(message = "cptName不能为空")
    private String publisher;
    @NotNull(message = "claim不能为空")
    private Map<String, Object> claim;

}
