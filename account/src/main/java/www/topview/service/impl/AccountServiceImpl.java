package www.topview.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.topview.constant.PathConstant;
import www.topview.entity.bo.CompanyRegisterBO;
import www.topview.entity.bo.UserRegisterBO;
import www.topview.entity.model.AccountModel;
import www.topview.entity.po.User;
import www.topview.exception.WeIdentityException;
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


    @Override
    public boolean userRegister(UserRegisterBO userRegisterBO) throws WeIdentityException {
        //为新用户注册weId
        AccountModel accountModel = weIdentityService.createWeId();

        /**
         * 以下这个PO尚未进行对账户密码的加密,用户私钥的加密,需要后续完成
         */
        User user = new User(
                null,
                userRegisterBO.getUsername(),
                userRegisterBO.getPassword(),
                accountModel.getWeId(),
                accountModel.getPublicKey(),
                //加密
                CryptoUtil.encrypt(accountModel.getPrivateKey(), PathConstant.PATH_PUBLIC_KEY)
                //解密方式
                //String encryptedKey
                //String decrypt = CryptoUtil.decrypt(encrypted, PathConstant.PATH_PRIVATE_KEY);
                ,
               "test" );

        //写入数据库
        Assert.isTrue(userMapper.insert(user) == 1, "注册失败,插入数据库失败");


        //调用合约
//        List<Object> params =new ArrayList<>();
//        params.add()

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
    public boolean companyRegister(CompanyRegisterBO companyRegisterBO) throws WeIdentityException {


        //数据库操作


        //调用合约
        return false;
    }
}
