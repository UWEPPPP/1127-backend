package www.topview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.topview.entity.model.RegisterCptModel;
import www.topview.entity.vo.CptInfoVO;
import www.topview.exception.WeIdentityException;
import www.topview.result.CommonResult;
import www.topview.service.DomainService;

/**
 * domain admin controller
 *
 * @author 刘家辉
 * @date 2023/10/31
 */
@RestController
@RequestMapping("/api/domainAdmin")
public class DomainAdminController {
    @Autowired
    DomainService domainService;

    @PostMapping("/addCompany")
    public void addCompany() {

    }

    @PostMapping("/getCompanyList")
    public void getCompanyList() {

    }

    @PostMapping("/deleteCompany")
    public void deleteCompany() {

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
