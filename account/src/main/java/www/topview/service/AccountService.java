package www.topview.service;

import www.topview.entity.bo.*;
import www.topview.entity.vo.ApplicationsVO;
import www.topview.exception.WeIdentityException;

import java.io.IOException;
import java.util.List;

/**
 * @author :Lictory
 * @date : 2023/10/27
 */
public interface AccountService {

    /**
     * 用户申请注册
     *
     * @param workerRegisterBO 包含用户注册信息
     * @return 返回bool
     * @throws WeIdentityException exception
     */
    boolean userRegister(WorkerRegisterBO workerRegisterBO) throws WeIdentityException;


    /**
     * 公司申请注册
     *
     * @param companyRegisterBO 公司注册信息
     * @return bool
     * @throws WeIdentityException WeIdentity
     */
    boolean companyRegister(CompanyRegisterBO companyRegisterBO) throws WeIdentityException;

    /**
     * 用户登录
     *
     * @param loginBO 登录信息
     * @return 登录是否成功
     */
    String login(LoginBO loginBO) throws IOException;


    /**
     * 查询申请列表
     *
     * @param queryApplicationsBO 对应的公司id
     * @return 申请列表
     */
    List<ApplicationsVO> queryApplicationsForWorker(QueryApplicationsBO queryApplicationsBO);

    /**
     * 域管理员查询公司申请列表
     * @return 公司申请列表
     */
    List<ApplicationsVO> queryApplicationsForCompany();

    /**
     * 通过申请的操作
     *
     * @param judgeWorkerBO 带有申请对象的主键id
     * @return 操作成功与否
     * @throws WeIdentityException exception
     */
    boolean judgeWorker(JudgeWorkerBO judgeWorkerBO) throws WeIdentityException;


    /**
     *通过公司申请
     * @param judgeCompanyBO 带有申请对象的主键id
     * @return 操作成功与否
     * @throws WeIdentityException exception
     */
    boolean judgeCompany(JudgeCompanyBO judgeCompanyBO) throws WeIdentityException;

}
