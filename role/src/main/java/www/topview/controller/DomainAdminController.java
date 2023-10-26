package www.topview.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/domainAdmin")
@ControllerAdvice
public class DomainAdminController {
    @PostMapping("/addCompany")
    public void addCompany() {

    }

    @PostMapping("/getCompanyList")
    public void getCompanyList() {

    }

    @PostMapping("/deleteCompany")
    public void deleteCompany() {

    }

}
