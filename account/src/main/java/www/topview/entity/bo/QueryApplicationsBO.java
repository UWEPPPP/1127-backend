package www.topview.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author :Lictory
 * @date : 2023/10/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QueryApplicationsBO {
    private Integer companyId;
    private Integer domainId;
}
