package www.topview.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import www.topview.entity.po.CompanyAdminInfo;

/**
 * admin mapper
 *
 * @author 刘家辉
 * @date 2023/10/31
 */

@Component
public interface CompanyAdminMapper extends BaseMapper<CompanyAdminInfo> {
}
