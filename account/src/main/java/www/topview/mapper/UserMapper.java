package www.topview.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import www.topview.entity.po.User;

/**
 * user mapper
 *
 * @author 刘家辉
 * @date 2023/11/01
 */

@Component
public interface UserMapper extends BaseMapper<User> {
}
