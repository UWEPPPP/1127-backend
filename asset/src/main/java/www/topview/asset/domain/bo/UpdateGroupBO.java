package www.topview.asset.domain.bo;

import lombok.Data;

/**
 * 修改资产组别的bo类
 * @author lql
 * @date 2023/10/30
 */
@Data
public class UpdateGroupBO {
    /**
     * 资产id
     */
    private Long assetId;

    /**
     * 组别名字
     */
    private String groupName;
}
