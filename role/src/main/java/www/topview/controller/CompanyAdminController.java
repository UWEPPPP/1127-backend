package www.topview.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companyAdmin")
public class CompanyAdminController {

    @PostMapping("/addWorker")
    public void addWorker() {

    }

    @PostMapping("/getWorkerList")
    public void getWorkerList() {

    }

    @PostMapping("/deleteWorker")
    public void deleteWorker() {

    }

    @PostMapping("/openDataGroup")
    public void openDataGroup() {

    }

    @PostMapping("/closeDataGroup")
    public void closeDataGroup() {

    }
}
