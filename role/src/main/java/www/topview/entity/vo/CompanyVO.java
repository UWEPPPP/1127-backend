package www.topview.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * company vo
 *
 * @author 刘家辉
 * @date 2023/10/31
 */
@Data
@Accessors(chain = true)
public class CompanyVO {
    private Integer companyId;
    private String companyName;
    private String companyContractAddress;
    private String adminWeId;
    private String adminName;
}
