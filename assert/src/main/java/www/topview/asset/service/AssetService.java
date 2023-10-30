package www.topview.asset.service;

import www.topview.asset.domain.dto.CreateAssetDTO;
import www.topview.asset.domain.vo.AssetDetailsVO;

/**
 * 资产service
 *
 * @author lql
 * @date 2023/10/29
 */
public interface AssetService {
    /**
     * 创建资产
     * @param createAssetDTO 创建资产dto
     * @return 创建成功返回true
     */
    Boolean createAsset(CreateAssetDTO createAssetDTO);

    /**
     * 通过id获取信息
     * @param id 资产id
     * @return 用于显示资产详细信息的vo
     */
    AssetDetailsVO getAssetMessage(Integer id);
}
