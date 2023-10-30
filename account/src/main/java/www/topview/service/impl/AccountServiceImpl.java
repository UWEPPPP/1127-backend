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
import www.topview.entity.po.ApplicationForUser;
import www.topview.entity.po.Company;
import www.topview.entity.po.User;
import www.topview.entity.vo.ApplicationUserVO;
import www.topview.exception.WeIdentityException;
import www.topview.mapper.ApplicationMapper;
import www.topview.mapper.CompanyMapper;
import www.topview.mapper.UserMapper;
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
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean userRegister(UserRegisterBO userRegisterBO) throws WeIdentityException {


        //判断账号是否已经存在
        QueryWrapper<ApplicationForUser> queryWrapper = Wrappers.query();
        queryWrapper.eq("applicant_username", userRegisterBO.getUsername());
        Assert.isTrue(applicationMapper.selectOne(queryWrapper) == null, "对应账户已经存在");


        //封装PO对象
        ApplicationForUser applicationForUser = new ApplicationForUser(
                null,
                null,
                2,
                userRegisterBO.getUsername(),
                CryptoUtil.encrypt(userRegisterBO.getPassword(), PathConstant.PATH_PUBLIC_KEY),
                userRegisterBO.getPayload(),
                1
        );
        //添加到申请列表中
        Assert.isTrue(applicationMapper.insert(applicationForUser) == 1, "注册申请失败,数据库插入异常");
        //调用合约
        return true;
    }


    /**
     * 公司进行注册
     *
     * @param companyRegisterBO 包含公司的注册信息
     * @return
     * @throws WeIdentityException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean companyRegister(CompanyRegisterBO companyRegisterBO) throws WeIdentityException {

        AccountModel accountModel = weIdentityService.createWeId();
        Company company = new Company(
                null,
                accountModel.getWeId(),
                null,
                CryptoUtil.encrypt(accountModel.getPrivateKey(), PathConstant.PATH_PUBLIC_KEY),
                accountModel.getPublicKey(),
                accountModel.getAccountAddress()
        );
        // TODO     进行企业注册
        Assert.isTrue(companyMapper.insert(company) == 1, "公司创建失败,数据库更新时发生异常");
        //TODO 进行企业管理员的注册
        Company newCompany = companyMapper.selectOne(new QueryWrapper<Company>().eq("account_address", accountModel.getAccountAddress()));
        Integer companyId = newCompany.getId();
        User user = new User(
                null,
                companyRegisterBO.getUsername(),
                companyRegisterBO.getPassword(),
                accountModel.getWeId(),
                accountModel.getPublicKey(),
                CryptoUtil.encrypt(accountModel.getPrivateKey(), PathConstant.PATH_PUBLIC_KEY),
                accountModel.getAccountAddress(),
                0
        );
        Assert.isTrue(userMapper.insert(user) == 1, "企业管理员注册失败");
        //  TODO 调用合约
        return true;
    }

    @Override
    public boolean login(LoginBO loginBO) {
        return true;
    }

    @Override
    public List<ApplicationUserVO> queryApplications(QueryApplicationsBO queryApplicationsBO) {
        Integer companyId = queryApplicationsBO.getCompanyId();
        List<ApplicationForUser> applications = applicationMapper.selectList(new QueryWrapper<ApplicationForUser>().eq("company_id", companyId));
        List<ApplicationUserVO> result = new ArrayList<>();
        for (ApplicationForUser temp : applications) {
            result.add(new ApplicationUserVO(
                    temp.getId(),
                    temp.getCompanyId(),
                    temp.getStatus(),
                    temp.getPayload()));
        }
        return result;
    }

    @Override
    public boolean judge(JudgeBO judgeBO) {

        applicationMapper.update(null,
                new UpdateWrapper<ApplicationForUser>().
                        set("status", judgeBO.getStatus()).
                        eq("id", judgeBO.getId()));
        return true;
    }


}
