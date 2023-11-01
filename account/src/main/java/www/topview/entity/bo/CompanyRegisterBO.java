package www.topview.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Lictory
 * @date : 2023/10/29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRegisterBO {

    /**
     * 账号,由申请者线下进行提供
     */
    private String username;

    /**
     * 密码,由申请者线下进行提供
     */
    private String password;

    /**
     * domain id
     */
    private Integer domainId;

    /**
     * 公司名字
     */
    private String companyName;
}
