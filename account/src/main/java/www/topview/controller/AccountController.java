package www.topview.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.topview.entity.bo.UserRegisterBO;
import www.topview.result.CommonResult;
import www.topview.service.AccountService;

import javax.annotation.Resource;

/**
 * @author :Lictory
 * @date : 2023/10/27
 */


@RestController
@RequestMapping("")
public class AccountController {

    @Resource
    private AccountService accountService;


    /**
     *  注册功能
     */
    @PostMapping("/register")
    CommonResult<Void> register(UserRegisterBO userRegisterBO) {
        return CommonResult.operateSuccess("注册成功", accountService.register(userRegisterBO));
    }

}
