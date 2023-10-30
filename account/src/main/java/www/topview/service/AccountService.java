package www.topview.service;

import www.topview.entity.bo.*;
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


    /**
     * 通过申请的操作
     * @param judgeBO 带有申请对象的主键id
     * @return 操作成功与否
     */
    boolean judge(JudgeBO judgeBO);

}
