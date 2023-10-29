package www.topview.service;

import www.topview.entity.bo.UserRegisterBO;
import www.topview.exception.WeIdentityException;

/**
 * @author :Lictory
 * @date : 2023/10/27
 */
public interface AccountService {

    /**
     * 注册
     * @param userRegisterBO 包含注册信息
     * @return 返回注册结果
     */
     boolean companyRegister(UserRegisterBO userRegisterBO) throws WeIdentityException;

    boolean userRegister(UserRegisterBO userRegisterBO) throws WeIdentityException;

}
