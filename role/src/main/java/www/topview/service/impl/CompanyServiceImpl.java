package www.topview.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import www.topview.dao.CompanyMapper;
import www.topview.dao.WorkerMapper;
import www.topview.dto.ChainServiceDTO;
import www.topview.entity.po.Company;
import www.topview.entity.po.Worker;
import www.topview.entity.vo.WorkerVO;
import www.topview.result.CommonResult;
import www.topview.rpc.ContractService;

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
    private WorkerMapper workerMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public void addWorker() {
        String header = request.getHeader("token");
        //TODO 尚未完成 等待token


        //TODO 尚未完成 等待链端接口
        //调合约接口 将员工注册进合约
        //调数据库 将员工的公司信息加入表里


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWorker(int workerId) {
        String header = request.getHeader("token");
        //TODO 尚未完成 等待token
        Integer id = null;

        Worker worker = workerMapper.selectById(workerId);
        Assert.notNull(worker, "该员工不存在");
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(worker.getAddress());
        ChainServiceDTO chainServiceDTO = new ChainServiceDTO();
        chainServiceDTO.setUserId(id).setContractName("CompanyLogic").setFunctionName("removedWorker").setFunctionParams(objects);
        CommonResult<Object> send = contractService.send(chainServiceDTO);
        Assert.isTrue(send.getCode() == 200, "调用合约删除员工失败");
        QueryWrapper<Worker> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", workerId);
        workerMapper.updateById(worker);
        worker.setCompanyId(-1)
                .setPrivateKey("")
                .setPublicKey("")
                .setAddress("")
                .setGroupName("")
                .setWeIdUser("");
        int update = workerMapper.update(worker, queryWrapper);
        Assert.isTrue(update == 1, "调用数据库删除员工失败");
    }

    @Override
    public List<WorkerVO> getWorkerList() {
        String header = request.getHeader("token");
        //TODO 尚未完成 等待token
        Integer id = null;
        QueryWrapper<Company> companyWrapper = new QueryWrapper<>();
        companyWrapper.eq("register_id", id);
        Company company = companyMapper.selectOne(companyWrapper);
        Assert.notNull(company, "用户id异常 无法查询到公司");
        QueryWrapper<Worker> userWrapper = new QueryWrapper<>();
        userWrapper.eq("company_id", company.getId());
        List<Worker> workers = workerMapper.selectList(userWrapper);
        ArrayList<WorkerVO> workerList = new ArrayList<>();
        for (Worker worker : workers) {
            workerList.add(new WorkerVO(worker));
        }
        return workerList;
    }
}
