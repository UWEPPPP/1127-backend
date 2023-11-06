package www.topview.service;

import www.topview.dto.UserDTO;

/**
 * user服务
 * @author lql
 * @date 2023/11/06
 */
public interface UserService {
    /**
     * 获取用户信息
     * @param id 用户id
     * @return 用户信息dto
     */
    UserDTO getUserMessage(Integer id);
}
