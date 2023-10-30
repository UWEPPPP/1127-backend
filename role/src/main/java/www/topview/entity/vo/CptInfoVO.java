package www.topview.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * cpt info vo
 *
 * @author 刘家辉
 * @date 2023/10/30
 */
@Data
@Accessors(chain = true)
public class CptInfoVO {
    private Integer cptId;
    private Integer cptVersion;
}
