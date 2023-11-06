package www.topview.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import www.topview.entity.po.WorkerInfo;

/**
 * @author :Lictory
 * @date : 2023/10/29
 */

@Component
public interface WorkerInfoMapper extends BaseMapper<WorkerInfo> {
    /**
     * 通过weId搜索
     * @param weId weid
     * @return 返回信息
     */
    default WorkerInfo selectByWeId(String weId){
        return this.selectOne(new QueryWrapper<WorkerInfo>().eq("weid", weId));
    }

}
