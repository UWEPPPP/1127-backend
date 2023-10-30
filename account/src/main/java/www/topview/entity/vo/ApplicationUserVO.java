package www.topview.entity.vo;

import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/10/30
 */

@Data
public class ApplicationUserVO {

    /**
     * 消息主键
     */
    private Integer id;

    /**
     * 申请的公司id
     */
    private Integer companyId;

    /**
     * 申请状态
     */
    private Integer status;

    /**
     * 用于公司员工证明身份
     */
    private String payload;

    public ApplicationUserVO() {
    }

    public ApplicationUserVO(Integer id, Integer companyId, Integer status, String payload) {
        this.id = id;
        this.companyId = companyId;
        this.status = status;
        this.payload = payload;
    }
}
