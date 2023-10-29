package www.topview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.topview.entity.model.RegisterCptModel;
import www.topview.result.CommonResult;
import www.topview.service.DomainService;

@RestController
@RequestMapping("/api/domainAdmin")
@ControllerAdvice
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
    public CommonResult<Void> registerCpt(@RequestBody RegisterCptModel model) {
       return CommonResult.operateSuccess("Register cpt Success",domainService.registerCpt(model));
    }

    @PostMapping("/getCptTemplate")
    public CommonResult<String> getCptTemplate() {
        return CommonResult.operateSuccess("Get template Success",domainService.getCptTemplate());
    }

}
