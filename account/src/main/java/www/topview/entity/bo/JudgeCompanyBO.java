package www.topview.entity.bo;

import lombok.Data;

/**
 * @author :Lictory
 * @date : 2023/11/06
 */
@Data
public class JudgeCompanyBO {

    /**
     * 记录主键
     */
    private Integer id;


    /**
     * 通过与否
     */
    private Integer status;

    public JudgeCompanyBO() {
    }

    public JudgeCompanyBO(Integer id, Integer status) {
        this.id = id;
        this.status = status;
    }
}
