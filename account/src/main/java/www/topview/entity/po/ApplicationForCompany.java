package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/11/06
 */

@Data
@TableName("application_company")
public class ApplicationForCompany {

    /**
     * id主键
     */
    private Integer id;

    /**
     * 域id
     */
    private Integer domainId;

    /**
     * 公司名字
     */
    private String companyName;

    /**
     * 证明材料
     */
    private String payLoad ;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 审核状态
     */
    private Integer status;

    public ApplicationForCompany(Integer id, Integer domainId, String companyName, String payLoad, String username, String password, Integer status) {
        this.id = id;
        this.domainId = domainId;
        this.companyName = companyName;
        this.payLoad = payLoad;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public ApplicationForCompany() {
    }

}
