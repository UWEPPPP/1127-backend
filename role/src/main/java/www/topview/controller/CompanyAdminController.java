package www.topview.controller;

import org.checkerframework.checker.index.qual.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import www.topview.dto.AddWorkerDTO;
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
 * @eo.api-type http
 * @eo.groupName 默认分组
 * @eo.path /role/companyAdmin
 */
@RestController
@RequestMapping("/role/companyAdmin")
@ResponseBody
public class CompanyAdminController {
    @Autowired
    private CompanyService service;

    /**
     * @param addWorkerDTO
     * @return CommonResult
     * @eo.name addWorker
     * @eo.url /addWorker
     * @eo.method post
     * @eo.request-type formdata
     */
    @PostMapping("/addWorker")
    public CommonResult<Void> addWorker(AddWorkerDTO addWorkerDTO) throws WeIdentityException {
        service.addWorker(addWorkerDTO);
        return CommonResult.operateSuccess("添加成功");
    }


    /**
     * @return CommonResult
     * @eo.name getWorkerList
     * @eo.url /getWorkerList
     * @eo.method post
     * @eo.request-type formdata
     */
    @PostMapping("/getWorkerList")
    public CommonResult<List<WorkerVO>> getWorkerList() {
        return CommonResult.operateSuccess("获取员工列表成功", service.getWorkerList());
    }

    /**
     * @param workerId
     * @return CommonResult
     * @eo.name deleteWorker
     * @eo.url /deleteWorker
     * @eo.method post
     * @eo.request-type formdata
     */
    @PostMapping("/deleteWorker")
    public CommonResult<Void> deleteWorker(@Positive int workerId) {
        service.deleteWorker(workerId);
        return CommonResult.operateSuccess("删除成功");
    }
}
