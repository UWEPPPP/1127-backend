package www.topview.entity.bo;

import lombok.Data;


/**
 * @author :Lictory
 * @date : 2023/10/29
 */

@Data
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

    /**
     * 证明材料
     */
    private String payLoad;

    public CompanyRegisterBO(String username, String password, Integer domainId, String companyName, String payLoad) {
        this.username = username;
        this.password = password;
        this.domainId = domainId;
        this.companyName = companyName;
        this.payLoad = payLoad;
    }

    public CompanyRegisterBO() {
    }
}
