package www.topview.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * register worker bo
 *
 * @author 刘家辉
 * @date 2023/11/03
 */
@Data
@Accessors(chain = true)
public class AddWorkerBO {
    private String username;
    private String password;
    private String groupName;
}
