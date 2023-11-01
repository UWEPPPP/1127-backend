package www.topview.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.topview.constant.PathConstant;
import www.topview.entity.bo.*;
import www.topview.entity.model.AccountModel;
import www.topview.entity.po.*;
import www.topview.entity.vo.ApplicationWorkerVO;
import www.topview.exception.WeIdentityException;
import www.topview.mapper.*;
import www.topview.service.AccountService;
import www.topview.service.WeIdentityService;
import www.topview.util.CryptoUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author :Lictory
 * @date : 2023/10/27
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private WeIdentityService weIdentityService;

    @Autowired
    private WorkerInfoMapper workerInfoMapper;

    @Autowired
    private CompanyAdminInfoMapper companyAdminMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean userRegister(WorkerRegisterBO workerRegisterBO) throws WeIdentityException {


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
     * @throws WeIdentityException we identity exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean companyRegister(CompanyRegisterBO companyRegisterBO) throws WeIdentityException {


        //由于公司表跟worker表中的主键具有相互依赖,故进行间接创建
        //根据accountAddress进行公司Id的创建
        AccountModel accountModel = weIdentityService.createWeId();
        Company company = new Company(
                null,
                null,
                accountModel.getAccountAddress(),
                companyRegisterBO.getDomainId(),
                companyRegisterBO.getCompanyName()
        );
        //  进行企业注册
        Assert.isTrue(companyMapper.insert(company) == 1, "公司创建失败,数据库更新时发生异常");
        // 进行企业管理员的注册
        Company newCompany = companyMapper.selectOne(new QueryWrapper<Company>().eq("contract_address", accountModel.getAccountAddress()));
        Integer companyId = newCompany.getId();
        User user = new User(
                null,
                companyRegisterBO.getUsername(),
                companyRegisterBO.getPassword(),
                accountModel.getWeId(),
                accountModel.getAccountAddress(),
                accountModel.getPublicKey(),
                accountModel.getPrivateKey()
        );
        Assert.isTrue(userMapper.insert(user) == 1, "企业管理员注册失败");
        //根据公司Id进行userId的创建
        CompanyAdminInfo admin = new CompanyAdminInfo(
                null,
                accountModel.getWeId(),
                companyId,
                companyRegisterBO.getDomainId()
        );
        Assert.isTrue(companyAdminMapper.insert(admin) == 1, "企业管理员注册失败");

        //获取registerId
        Integer registerId = companyAdminMapper.selectOne(new QueryWrapper<CompanyAdminInfo>().eq("company_id", companyId)).getId();
        //将注册人的id更新至company中
        Company company1 = new Company();
        company1.setRegisterId(registerId);

        //  TODO 调用合约
        String contractAddr = null;
        companyMapper.update(company1, new UpdateWrapper<Company>().eq("contract_address", contractAddr));

        return true;
    }

    @Override
    public String login(LoginBO loginBO) {
        //加了role lictory注意自己看看

        //TODO 上JWT
        switch (loginBO.getRole()) {
            case 0:
                User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", loginBO.getUsername()));
                Assert.notNull(user, "登录失败,用户名不存在");
                break;
            case 1:
            case 2:
                break;
            default:
        }


        return "true";
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
    public boolean judgeWorker(JudgeBO judgeBO) throws WeIdentityException {

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
        AccountModel weId = weIdentityService.createWeId();
        User user = new User(
                null,
                applicationForUser.getApplicantUsername(),
                applicationForUser.getApplicantPassword(),
                weId.getWeId(),
                weId.getAccountAddress(),
                weId.getPublicKey(),
                weId.getPrivateKey()
        );
        Assert.isTrue(userMapper.insert(user) == 1, "新用户创建失败,数据库异常");
        WorkerInfo workerInfo = new WorkerInfo(
                null,
                weId.getWeId(),
                judgeBO.getGroupName(),
                applicationForUser.getCompanyId(),
                applicationForUser.getDomainId()
        );
        Assert.isTrue(workerInfoMapper.insert(workerInfo) == 1, "新用户创建失败,数据库异常");
        return true;
    }


}
