package www.topview.entity.bo;

import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/10/30
 */

@Data
public class JudgeBO {

    /**
     * 申请列表中的主键
     */
    private Integer id;


    /**
     * 审核结果
     */
    private Integer status;

}
