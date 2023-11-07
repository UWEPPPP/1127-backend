package www.topview.controller;

import org.checkerframework.checker.index.qual.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.topview.dto.AddCompanyDTO;
import www.topview.entity.bo.RegisterCptBO;
import www.topview.entity.vo.CompanyVO;
import www.topview.entity.vo.CptInfoVO;
import www.topview.exception.WeIdentityException;
import www.topview.result.CommonResult;
import www.topview.service.DomainService;

import java.util.List;

/**
 * domain admin controller
 *
 * @author 刘家辉
 * @date 2023/10/31
 */
@RestController
@RequestMapping("/domainAdmin")
@ResponseBody
public class DomainAdminController {
    @Autowired
    DomainService domainService;

    @PostMapping("/registerCompany")
    public CommonResult<Void> addCompany(@Validated @RequestBody AddCompanyDTO addCompanyDTO) throws WeIdentityException {
        domainService.addCompany(addCompanyDTO);
        return CommonResult.operateSuccess("公司创建成功");
    }

    @PostMapping("/getCompanyList")
    public CommonResult<List<CompanyVO>> getCompanyList() {
        return CommonResult.operateSuccess("成功获取公司信息列表", domainService.getCompanyList());
    }

    @PostMapping("/deleteCompany/{domainId}")
    public CommonResult<Void> deleteCompany(@Positive @PathVariable int domainId) {
        domainService.deleteCompany(domainId);
        return CommonResult.operateSuccess("公司删除成功");
    }

    @PostMapping("/registerCpt")
    public CommonResult<CptInfoVO> registerCpt(@Validated @RequestBody RegisterCptBO model) throws WeIdentityException {
        return CommonResult.operateSuccess("Register cpt Success", domainService.registerCpt(model));
    }

    @PostMapping("/getCptTemplate")
    public CommonResult<String> getCptTemplate() {
        return CommonResult.operateSuccess("Get template Success", domainService.getCptTemplate());
    }

}
