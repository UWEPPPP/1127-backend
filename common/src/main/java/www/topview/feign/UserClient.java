package www.topview.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import www.topview.dto.UserDTO;
import www.topview.result.CommonResult;

/**
 * 用户相关的接口
 *
 * @author lql
 * @date 2023/11/06
 */
@FeignClient("user")
public interface UserClient {
    /**
     * 获取用户信息
     * @param id 用户id
     * @return 返回用户信息
     */
    @GetMapping("/user/{id}")
    CommonResult<UserDTO> getUserMessage(@PathVariable Integer id);
}
