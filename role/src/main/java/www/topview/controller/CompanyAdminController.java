package www.topview.controller;

import cn.hutool.jwt.JWT;
import org.checkerframework.checker.index.qual.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import www.topview.dto.AddWorkerDTO;
import www.topview.dto.PayLoad;
import www.topview.entity.bo.AddWorkerBO;
import www.topview.entity.po.CompanyAdminInfo;
import www.topview.entity.vo.WorkerVO;
import www.topview.exception.WeIdentityException;
import www.topview.result.CommonResult;
import www.topview.service.CompanyService;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private HttpServletRequest request;

    /**
     * 被账户服务调用的方法
     *
     * @param addWorkerDTO
     * @return CommonResult
     * @eo.name addWorker
     * @eo.url /addWorker
     * @eo.method post
     * @eo.request-type formdata
     */
    @PostMapping("/registerWorker")
    public CommonResult<Void> addWorker(AddWorkerDTO addWorkerDTO) throws WeIdentityException {
        service.addWorker(addWorkerDTO);
        return CommonResult.operateSuccess("添加成功");
    }

    /**
     * 前端直接调用的方法
     * add worker
     *
     * @param addWorkerBO add worker bo
     * @return {@link CommonResult}<{@link Void}>
     * @throws WeIdentityException weidentity exception
     */
    @PostMapping("/addWorker")
    public CommonResult<Void> addWorker(AddWorkerBO addWorkerBO) throws WeIdentityException {
        JWT jwt = JWT.of(request.getHeader("token"));
        PayLoad payload = (PayLoad) jwt.getPayload("payload");
        Integer id = payload.getUserId();
        CompanyAdminInfo companyByAdminId = service.getCompanyByAdminId(id);
        AddWorkerDTO addWorkerDTO = new AddWorkerDTO();
        addWorkerDTO.setCompanyId(companyByAdminId.getCompanyId())
                .setDomainId(companyByAdminId.getDomainId())
                .setPasser(id)
                .setUsername(addWorkerBO.getUsername())
                .setPassword(addWorkerBO.getPassword())
                .setGroupName(addWorkerBO.getGroupName());
        service.addWorker(addWorkerDTO);
        return CommonResult.operateSuccess("注册成功");
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
