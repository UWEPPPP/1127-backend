package www.topview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.topview.entity.bo.*;
import www.topview.entity.vo.ApplicationWorkerVO;
import www.topview.exception.WeIdentityException;
import www.topview.result.CommonResult;
import www.topview.service.AccountService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

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
     * 注册功能
     */
    @PostMapping("/userRegisterApplicant")
    CommonResult<Boolean> userRegister(WorkerRegisterBO workerRegisterBO) throws WeIdentityException {
        return CommonResult.operateSuccess("申请注册成功", accountService.userRegister(workerRegisterBO));
    }

    /**
     * 合约部署者调用该方法对公司以及公司管理员进行注册
     *
     * @param companyRegisterBO
     * @return
     * @throws WeIdentityException
     */
    @PostMapping("/companyRegisterApplicant")
    CommonResult<Boolean> companyRegister(CompanyRegisterBO companyRegisterBO) throws WeIdentityException {
        return CommonResult.operateSuccess("申请注册成功", accountService.companyRegister(companyRegisterBO));
    }

    /**
     * 返回token
     *
     * @param loginBO 用户的账户密码
     * @return token
     */
    @PostMapping("/login")
    CommonResult<String> login(LoginBO loginBO) throws IOException {
        return CommonResult.operateSuccess("登录成功", accountService.login(loginBO));
    }

    @GetMapping("/queryApplications")
    CommonResult<List<ApplicationWorkerVO>> queryApplications(QueryApplicationsBO queryApplicationsBO) {
        return CommonResult.operateSuccess("获取申请列表成功", accountService.queryApplications(queryApplicationsBO));
    }

    @PostMapping("/judge")
    CommonResult<Boolean> judge(JudgeBO judgeBO) throws WeIdentityException {
        return CommonResult.operateSuccess("操作成功", accountService.judgeWorker(judgeBO));
    }

}
