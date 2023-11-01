package www.topview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.topview.entity.bo.AddWorkerBO;
import www.topview.entity.vo.WorkerVO;
import www.topview.exception.WeIdentityException;
import www.topview.result.CommonResult;
import www.topview.service.CompanyService;

import java.util.List;

/**
 * company admin controller
 *
 * @author 刘家辉
 * @date 2023/10/30
 */
@RestController
@RequestMapping("/companyAdmin")
public class CompanyAdminController {
    @Autowired
    private CompanyService service;

    @PostMapping("/addWorker")
    public CommonResult<Void> addWorker(AddWorkerBO addWorkerBO) throws WeIdentityException {
        service.addWorker(addWorkerBO);
        return CommonResult.operateSuccess("添加成功");
    }


    @PostMapping("/getWorkerList")
    public CommonResult<List<WorkerVO>> getWorkerList() {
        return CommonResult.operateSuccess("获取员工列表成功", service.getWorkerList());
    }

    @PostMapping("/deleteWorker")
    public CommonResult<Void> deleteWorker(int workerId) {
        service.deleteWorker(workerId);
        return CommonResult.operateSuccess("删除成功");
    }
}
