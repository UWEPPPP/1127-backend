package www.topview.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * company admin controller
 *
 * @author 刘家辉
 * @date 2023/10/30
 */
@RestController
@RequestMapping("/api/companyAdmin")
public class CompanyAdminController {

    @PostMapping("/getAllApplyFromWorker")
    public void getAllApplyFromWorker() {

    }

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
