package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Lictory
 * @date : 2023/10/30
 */

@Data
@TableName("application_user")
public class ApplicationForUser {

    /**
     * 用户主键(自增)
     */
    private Integer id;

    /**
     * 申请公司的ID
     */
    @TableField("company_id")
    private Integer companyId;

    /**
     * domain id
     */
    @TableField("domain_id")
    private Integer domainId;

    /**
     * 申请状态(1 表示申请通过  2  表示申请中  0表示申请失败)
     */
    private Integer status;

    /**
     * applicant username
     */
    @TableField("applicant_username")
    private String applicantUsername;

    /**
     * applicant password
     */
    @TableField("applicant_password")
    private String applicantPassword;

    /**
     * 员工证明材料
     */
    private String payload;

    public ApplicationForUser(Integer id, Integer companyId, Integer domainId, Integer status, String applicantUsername, String applicantPassword, String payload) {
        this.id = id;
        this.companyId = companyId;
        this.domainId = domainId;
        this.status = status;
        this.applicantUsername = applicantUsername;
        this.applicantPassword = applicantPassword;
        this.payload = payload;
    }

    public ApplicationForUser() {
    }
}
