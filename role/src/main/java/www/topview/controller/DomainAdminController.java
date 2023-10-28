package www.topview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.topview.result.CommonResult;
import www.topview.service.DomainService;
import www.topview.service.WeIdentityService;

import javax.swing.text.StyledEditorKit;

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
    public CommonResult<Boolean> registerCpt() {
       // return CommonResult.operateSuccess("Register cpt Success",domainService.registerCpt());
       return null;
    }

    @PostMapping("/getCptTemplate")
    public CommonResult<String> getCptTemplate() {
        return CommonResult.operateSuccess("Get template Success",domainService.getCptTemplate());
    }

}
