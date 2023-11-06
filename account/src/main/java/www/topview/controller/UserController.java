package www.topview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.topview.dto.UserDTO;
import www.topview.result.CommonResult;
import www.topview.service.UserService;

import javax.annotation.Resource;

/**
 * 用户controller
 * @author lql
 * @date 2023/11/06
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public CommonResult<UserDTO> getUserMessage(@PathVariable Integer id){
        return CommonResult.operateSuccess("获取成功",userService.getUserMessage(id));
    }
}
