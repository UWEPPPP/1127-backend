package www.topview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import www.topview.dto.CompanyDTO;
import www.topview.result.CommonResult;
import www.topview.service.CompanyService;

import javax.annotation.Resource;

/**
 * 企业controller
 * @author lql
 * @date 2023/11/06
 */
@RestController
@RequestMapping("company")
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @GetMapping("/{weId}")
    public CommonResult<CompanyDTO> getCompanyMessage(@PathVariable String weId){
        return CommonResult.operateSuccess("获取成功",companyService.getCompanyMessage(weId));
    }
}
