package www.topview.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.topview.entity.bo.CompanyRegisterBO;
import www.topview.entity.bo.LoginBO;
import www.topview.entity.bo.UserRegisterBO;
import www.topview.exception.WeIdentityException;
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
    @PostMapping("/userRegisterApplicant")
    CommonResult<Boolean> userRegister(UserRegisterBO userRegisterBO) throws WeIdentityException {
        return CommonResult.operateSuccess("申请注册成功", accountService.userRegister(userRegisterBO));
    }

    /**
     * 合约部署者调用该方法对公司进行注册
     * @param companyRegisterBO
     * @return
     * @throws WeIdentityException
     */
    @PostMapping("/companyRegisterApplicant")
    CommonResult<Boolean> companyRegister(CompanyRegisterBO companyRegisterBO) throws WeIdentityException {
        return CommonResult.operateSuccess("申请注册成功", accountService.companyRegister(companyRegisterBO));
    }

    @PostMapping("/login")
    CommonResult<Boolean> login(LoginBO loginBO)  {
        return CommonResult.operateSuccess("登录成功", accountService.login(loginBO));
    }



}
