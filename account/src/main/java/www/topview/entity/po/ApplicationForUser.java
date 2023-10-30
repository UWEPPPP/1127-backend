package www.topview.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
     *  申请状态(1 表示申请通过  2  表示申请中  0表示申请失败)
     */
    private Integer status;

    @TableField("applicant_username")
    private String applicantUsername;

    @TableField("applicant_password")
    private String applicantPassword;

    /**
     * 员工证明材料
     */
    private String payload;

    public ApplicationForUser(Integer id, Integer companyId, Integer status, String applicantUsername, String applicantPassword, String payload) {
        this.id = id;
        this.companyId = companyId;
        this.status = status;
        this.applicantUsername = applicantUsername;
        this.applicantPassword = applicantPassword;
        this.payload = payload;
    }

    public ApplicationForUser() {
    }
}
