package www.topview.service.impl;

import com.alibaba.spring.util.BeanUtils;
import org.springframework.stereotype.Service;
import www.topview.dto.UserDTO;
import www.topview.entity.po.User;
import www.topview.mapper.UserMapper;
import www.topview.service.UserService;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 用户service实现类
 * @author lql
 * @date 2023/11/06
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDTO getUserMessage(Integer id) {
        User user = userMapper.selectById(id);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户信息查询不到");
        }

        return new UserDTO()
                .setId(user.getId())
                .setRole(user.getRole())
                .setAddress(user.getAddress())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setWeId(user.getWeId())
                .setPrivateKey(user.getPrivateKey())
                .setPublicKey(user.getPublicKey());
    }
}
