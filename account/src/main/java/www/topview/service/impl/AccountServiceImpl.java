package www.topview.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.topview.constant.PathConstant;
import www.topview.dto.AddCompanyDTO;
import www.topview.dto.AddWorkerDTO;
import www.topview.dto.CreateProcessorDTO;
import www.topview.dto.PayLoad;
import www.topview.entity.bo.*;
import www.topview.entity.po.ApplicationForUser;
import www.topview.entity.po.User;
import www.topview.entity.vo.ApplicationWorkerVO;
import www.topview.mapper.ApplicationMapper;
import www.topview.mapper.UserMapper;
import www.topview.result.CommonResult;
import www.topview.rpc.ChainService;
import www.topview.rpc.RoleService;
import www.topview.service.AccountService;
import www.topview.util.CryptoUtil;
import www.topview.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author :Lictory
 * @date : 2023/10/27
 */

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ChainService chainService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean userRegister(WorkerRegisterBO workerRegisterBO) {


        //判断账号是否已经存在
        QueryWrapper<ApplicationForUser> queryWrapper = Wrappers.query();
        queryWrapper.eq("applicant_username", workerRegisterBO.getUsername());
        Assert.isTrue(applicationMapper.selectOne(queryWrapper) == null, "对应账户已经存在");

        //封装PO对象
        ApplicationForUser applicationForUser = new ApplicationForUser(
                null,
                null,
                workerRegisterBO.getDomainId(),
                2,
                workerRegisterBO.getUsername(),
                CryptoUtil.encrypt(workerRegisterBO.getPassword(), PathConstant.PATH_PUBLIC_KEY),
                workerRegisterBO.getPayload()
        );
        //添加到申请列表中
        Assert.isTrue(applicationMapper.insert(applicationForUser) == 1, "注册申请失败,数据库插入异常");
        //调用合约
        return true;
    }


    /**
     * company register
     * 公司进行注册
     *
     * @param companyRegisterBO 包含公司的注册信息
     * @return boolean
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean companyRegister(CompanyRegisterBO companyRegisterBO) {

        String token=request.getHeader("token");
        JWT jwt = JWT.of(token);
        //token异常包括了    1. 过期 2. 签名不对
        Assert.isTrue(jwtUtil.validateToken(token), "token异常");

        PayLoad payload = (PayLoad) jwt.getPayload("payload");
        Integer adminId = payload.getUserId();

        AddCompanyDTO addCompanyDTO = new AddCompanyDTO();
        addCompanyDTO.setCompanyName(companyRegisterBO.getCompanyName())
                .setFounderName(companyRegisterBO.getUsername())
                .setPasser(adminId)
                .setFounderPassword(companyRegisterBO.getPassword())
                .setDomainId(companyRegisterBO.getDomainId());
        CommonResult<Void> voidCommonResult = roleService.addCompany(addCompanyDTO);
        Assert.isTrue(voidCommonResult.getCode() == 200, "调用角色服务添加公司失败");
        return true;
    }

    @Override
    public String login(LoginBO loginBO) throws IOException {

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", loginBO.getUsername()));

        Assert.notNull(user, "登录失败,用户名不存在");
        Assert.isTrue(CryptoUtil.decrypt(user.getPassword(),
                                PathConstant.PATH_PRIVATE_KEY).
                        equals(loginBO.getPassword())
                , "登录失败,账户名或密码错误");

        //TODO 调用链端
        CreateProcessorDTO createProcessorDTO = new CreateProcessorDTO();
        createProcessorDTO.setUserId(user.getId())
                .setPrivateKey(CryptoUtil.decrypt(user.getPrivateKey(), PathConstant.PATH_PRIVATE_KEY));
        CommonResult<Void> voidCommonResult = chainService.create(createProcessorDTO);
        Assert.isTrue(voidCommonResult.getCode() == 200, "调用链端创建processor失败");

        PayLoad payLoad = new PayLoad(user.getId());
        return jwtUtil.createJwtToken(loginBO.getRole().toString(), payLoad);

    }

    @Override
    public List<ApplicationWorkerVO> queryApplications(QueryApplicationsBO queryApplicationsBO) {
        List<ApplicationForUser> applications = applicationMapper.selectList(new QueryWrapper<ApplicationForUser>()
                .eq("company_id", queryApplicationsBO.getCompanyId())
                .eq("domain_id", queryApplicationsBO.getDomainId()));
        List<ApplicationWorkerVO> result = new ArrayList<>();
        for (ApplicationForUser temp : applications) {
            //TODO ApplicationUserVO 是否需要公司id
            result.add(new ApplicationWorkerVO(
                    temp.getId(),
                    temp.getStatus(),
                    temp.getPayload()));
        }
        return result;
    }

    @Override
    public boolean judgeWorker(JudgeBO judgeBO) {

        String jwt = request.getHeader("token");
        JWT of = JWT.of(jwt);
        PayLoad payload = (PayLoad) of.getPayload("payload");
        Integer adminId = payload.getUserId();

        applicationMapper.update(null,
                new UpdateWrapper<ApplicationForUser>().
                        set("status", judgeBO.getStatus()).
                        eq("id", judgeBO.getId()));
        //判断是否通过申请      如果不通过则直接返回,通过则进行新用户创建操作
        if (judgeBO.getStatus() == 0) {
            return true;
        }
        ApplicationForUser applicationForUser = applicationMapper.selectOne(new QueryWrapper<ApplicationForUser>().eq("id", judgeBO.getId()));
        //从申请表中获取的密码已经是加密过的了.在这里无需再次加密
        AddWorkerDTO addWorkerDTO = new AddWorkerDTO();
        addWorkerDTO.setUsername(applicationForUser.getApplicantUsername())
                .setPassword(applicationForUser.getApplicantPassword())
                .setPasser(adminId)
                .setGroupName(judgeBO.getGroupName())
                .setDomainId(applicationForUser.getDomainId())
                .setCompanyId(applicationForUser.getCompanyId());
        CommonResult<Void> voidCommonResult = roleService.addWorker(addWorkerDTO);
        Assert.isTrue(voidCommonResult.getCode() == 200, "调用角色服务添加员工失败");
        return true;
    }

}
