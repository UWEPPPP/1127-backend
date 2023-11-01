package www.topview.controller;

import org.checkerframework.checker.index.qual.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.topview.entity.dto.AddCompanyDTO;
import www.topview.entity.model.RegisterCptModel;
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
@RequestMapping("/role/domainAdmin")
@ResponseBody
public class DomainAdminController {
    @Autowired
    DomainService domainService;

    @PostMapping("/addCompany")
    public CommonResult<Void> addCompany(AddCompanyDTO addCompanyDTO) throws WeIdentityException {
        domainService.addCompany(addCompanyDTO);
        return CommonResult.operateSuccess("公司创建成功");
    }

    @PostMapping("/getCompanyList")
    public CommonResult<List<CompanyVO>> getCompanyList() {
        return CommonResult.operateSuccess("成功获取公司信息列表", domainService.getCompanyList());
    }

    @PostMapping("/deleteCompany")
    public CommonResult<Void> deleteCompany(@Positive int domainId) throws WeIdentityException {
        domainService.deleteCompany(domainId);
        return CommonResult.operateSuccess("公司删除成功");
    }

    @PostMapping("/registerCpt")
    public CommonResult<CptInfoVO> registerCpt(@RequestBody RegisterCptModel model) throws WeIdentityException {
        return CommonResult.operateSuccess("Register cpt Success", domainService.registerCpt(model));
    }

    @PostMapping("/getCptTemplate")
    public CommonResult<String> getCptTemplate() {
        return CommonResult.operateSuccess("Get template Success", domainService.getCptTemplate());
    }

}
