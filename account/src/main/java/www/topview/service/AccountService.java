package www.topview.service;

import www.topview.entity.bo.CompanyRegisterBO;
import www.topview.entity.bo.LoginBO;
import www.topview.entity.bo.QueryApplicationsBO;
import www.topview.entity.bo.UserRegisterBO;
import www.topview.entity.po.Company;
import www.topview.entity.po.User;
import www.topview.entity.vo.ApplicationUserVO;
import www.topview.exception.WeIdentityException;

import java.util.List;

/**
 * @author :Lictory
 * @date : 2023/10/27
 */
public interface AccountService {

    /**
     * 用户申请注册
     * @param userRegisterBO    包含用户注册信息
     * @return  返回bool
     * @throws WeIdentityException exception
     */
     boolean userRegister(UserRegisterBO userRegisterBO) throws WeIdentityException;


    /**
     *公司申请注册
     * @param companyRegisterBO 公司注册信息
     * @return  bool
     * @throws WeIdentityException WeIdentity
     */
    boolean companyRegister(CompanyRegisterBO companyRegisterBO) throws WeIdentityException;

    /**
     * 用户登录
     * @param loginBO 登录信息
     * @return 登录是否成功
     */
    boolean login(LoginBO loginBO);


    List<ApplicationUserVO> queryApplications(QueryApplicationsBO queryApplicationsBO);
}
