package www.topview.dto;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * add worker bo
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@Data

@Accessors(chain = true)
public class AddWorkerDTO {
    private Integer passer;
    private String username;
    private String password;
    private Integer companyId;
    private Integer domainId;
    private String groupName;
}
