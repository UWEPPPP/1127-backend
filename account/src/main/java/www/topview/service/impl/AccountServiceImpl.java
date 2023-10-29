package www.topview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.topview.entity.bo.UserRegisterBO;
import www.topview.entity.model.AccountModel;
import www.topview.entity.po.User;
import www.topview.exception.WeIdentityException;
import www.topview.mapper.UserMapper;
import www.topview.service.AccountService;
import www.topview.service.WeIdentityService;


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

    @Override
    public boolean companyRegister(UserRegisterBO userRegisterBO) throws WeIdentityException {
        //为新用户注册weId
        AccountModel accountModel = weIdentityService.createWeId();

        /**
         * 以下这个PO尚未进行对账户密码的加密,用户私钥的加密,需要后续完成
         */
        User user=new User(
                accountModel.getWeId(),
                userRegisterBO.getUsername(),
                userRegisterBO.getPassword(),
                accountModel.getWeId(),
                accountModel.getPublicKey(),
                accountModel.getPrivateKey()
        );
        //写入数据库


        //调用合约

    }

    @Override
    public boolean userRegister(UserRegisterBO userRegisterBO) throws WeIdentityException {

        //为新用户注册weId
        AccountModel weId = weIdentityService.createWeId();

        //数据库操作


        //调用合约
        return false;
    }
}
