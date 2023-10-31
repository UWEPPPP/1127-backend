package www.topview.asset.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lql
 * @date 2023/10/30
 */
@Data
@Accessors(chain = true)
public class AssetVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 资产名字
     */
    private String name;

    /**
     * 创建者名字
     */
    private String creatorName;

    /**
     * 域名
     */
    private String domainName;

    /**
     * 公司/企业名
     */
    private String companyName;

    /**
     * 组名
     */
    private String groupName;
}
