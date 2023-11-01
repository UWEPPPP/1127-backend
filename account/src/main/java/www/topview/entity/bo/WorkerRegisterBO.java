package www.topview.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Lictory
 * @date : 2023/10/27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRegisterBO {


    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * domain id
     */
    private Integer domainId;

    /**
     * 员工属于公司的证明材料
     */
    private String payload;

}
