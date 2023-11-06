package www.topview.asset.domain.dto;

import lombok.Data;
import www.topview.asset.domain.bo.CreateAssetBO;

/**
 * 创建资产的dto
 * @author lql
 * @date 2023/10/30
 */
@Data
public class CreateAssetDTO {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 创建资产请求类
     */
    private CreateAssetBO createAssetBO;
}
