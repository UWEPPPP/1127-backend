package www.topview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.topview.entity.vo.WorkerVO;
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
@RequestMapping("/api/companyAdmin")
public class CompanyAdminController {
    @Autowired
    private CompanyService service;

    @PostMapping("/addWorker")
    public void addWorker() {

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
