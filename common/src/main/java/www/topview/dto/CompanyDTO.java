package www.topview.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 公司/企业dto
 * @author lql
 * @date 2023/11/06
 */
@Data
@Accessors(chain = true)
public class CompanyDTO {
    /**
     * 公司账户地址
     */
    private String contractAddress;

    /**
     * domain id
     */
    private Integer domainId;

    /**
     * 公司名字
     */
    private String companyName;
}
