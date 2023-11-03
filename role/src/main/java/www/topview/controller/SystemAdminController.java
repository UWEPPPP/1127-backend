package www.topview.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * system admin controller
 *
 * @author 刘家辉
 * @date 2023/10/31
 */
@RestController
@RequestMapping("/systemAdmin")
@ResponseBody
public class SystemAdminController {

    @PostMapping("/createDomain")
    public void addDomainAdmin() {

    }

}
