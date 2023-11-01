package www.topview.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.topview.constant.PathConstant;
import www.topview.constant.RoleConstant;
import www.topview.dao.CompanyAdminMapper;
import www.topview.dao.CompanyMapper;
import www.topview.dao.UserMapper;
import www.topview.dao.WorkerInfoMapper;
import www.topview.dto.ChainServiceDTO;
import www.topview.entity.dto.AddWorkerDTO;
import www.topview.entity.model.AccountModel;
import www.topview.entity.po.Company;
import www.topview.entity.po.CompanyAdminInfo;
import www.topview.entity.po.User;
import www.topview.entity.po.WorkerInfo;
import www.topview.entity.vo.WorkerVO;
import www.topview.exception.WeIdentityException;
import www.topview.result.CommonResult;
import www.topview.rpc.ContractService;
import www.topview.service.WeIdentityService;
import www.topview.util.CryptoUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘家辉
 * @date 2023/10/30
 */
@Service
public class CompanyServiceImpl implements www.topview.service.CompanyService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ContractService contractService;
    @Autowired
    private WorkerInfoMapper workerInfoMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeIdentityService weIdentityService;
    @Autowired
    private CompanyAdminMapper companyAdminMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWorker(AddWorkerDTO addWorkerDTO) throws WeIdentityException {
//        String header = request.getHeader("token"); 账户服务调用 无需token
//


        AccountModel weId = weIdentityService.createWeId();
        User user = new User(
                null,
                addWorkerDTO.getUsername(),
                addWorkerDTO.getPassword(),
                weId.getWeId(),
                weId.getAccountAddress(),
                weId.getPublicKey(),
                CryptoUtil.encrypt(weId.getPrivateKey(), PathConstant.PATH_PUBLIC_KEY)
                , RoleConstant.WORKER
        );
        Assert.isTrue(userMapper.insert(user) == 1, "新用户创建失败,数据库异常");
        WorkerInfo workerInfo = new WorkerInfo(
                null,
                weId.getWeId(),
                addWorkerDTO.getGroupName(),
                addWorkerDTO.getCompanyId(),
                addWorkerDTO.getDomainId()
        );
        Assert.isTrue(workerInfoMapper.insert(workerInfo) == 1, "新用户创建失败,数据库异常");

        //TODO 尚未完成 等待链端接口
        Company company = companyMapper.selectById(addWorkerDTO.getCompanyId());
        ChainServiceDTO chainServiceDTO = new ChainServiceDTO();

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(addWorkerDTO.getGroupName());
        objects.add(weId.getAccountAddress());
        chainServiceDTO.setUserId(addWorkerDTO.getPasser()).
                setContractName("CompanyLogic").
                setFunctionName("addWorker").
                setFunctionParams(objects).
                setContractAddress(company.getContractAddress());
        contractService.send(chainServiceDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWorker(int workerId) {
        String header = request.getHeader("token");
        //TODO 尚未完成 等待token
        String id = null;
        checkCompanyAdmin(id);

        User worker = userMapper.selectById(workerId);
        String weid = worker.getWeId();
        Assert.notNull(worker, "该员工不存在");
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(worker.getAddress());
        ChainServiceDTO chainServiceDTO = new ChainServiceDTO();
        chainServiceDTO.setUserId(Integer.valueOf(id)).setContractName("CompanyLogic").setFunctionName("removedWorker").setFunctionParams(objects);
        CommonResult<Object> send = contractService.send(chainServiceDTO);
        Assert.isTrue(send.getCode() == 200, "调用合约删除员工失败");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", workerId);
        worker.setPrivateKey("")
                .setPublicKey("")
                .setAddress("")
                .setWeId("");
        int update = userMapper.update(worker, queryWrapper);
        Assert.isTrue(update == 1, "调用数据库删除员工失败");
        QueryWrapper<WorkerInfo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("weid", weid);
        int delete = workerInfoMapper.delete(queryWrapper1);
        Assert.isTrue(delete == 1, "调用数据库删除员工失败");
    }

    @Override
    public List<WorkerVO> getWorkerList() {
        String header = request.getHeader("token");
        //TODO 尚未完成 等待token
        String id = null;

        checkCompanyAdmin(id);
        QueryWrapper<Company> companyWrapper = new QueryWrapper<>();
        companyWrapper.eq("register_id", id);
        Company company = companyMapper.selectOne(companyWrapper);
        Assert.notNull(company, "用户id异常 无法查询到公司");
        QueryWrapper<WorkerInfo> workerInfoWrapper = new QueryWrapper<>();
        workerInfoWrapper.eq("company_id", company.getId());
        List<WorkerInfo> workers = workerInfoMapper.selectList(workerInfoWrapper);
        ArrayList<WorkerVO> workerList = new ArrayList<>();
        for (WorkerInfo worker : workers) {
            QueryWrapper<User> userWrapper = new QueryWrapper<>();
            userWrapper.eq("weid", worker.getWeId());
            User user = userMapper.selectOne(userWrapper);
            workerList.add(new WorkerVO(worker, user));
        }
        return workerList;
    }

    public void checkCompanyAdmin(String id) {
        User admin = userMapper.selectById(id);
        Assert.notNull(admin, "操作者id不存在");
        CompanyAdminInfo weid = companyAdminMapper.selectOne(new QueryWrapper<CompanyAdminInfo>().eq("weid", admin.getWeId()));
        Assert.notNull(weid, "该用户不是公司管理员");
    }
}
