package www.topview.asset.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import www.topview.asset.domain.po.Asset;

import java.util.List;

/**
 * 资产类的mapper
 *
 * @author lql
 * @date 2023/10/29
 */
public interface AssetMapper extends BaseMapper<Asset> {
    /**
     * 查询有效的资产列表
     * @return 返回有效的资产列表
     */
    default List<Asset> selectValidList(){
        return this.selectList(new QueryWrapper<Asset>().eq("status",0));
    }
}
