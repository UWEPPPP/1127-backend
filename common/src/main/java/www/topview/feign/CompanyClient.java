package www.topview.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import www.topview.dto.CompanyDTO;
import www.topview.result.CommonResult;

/**
 * 公司/企业接口
 * @author lql
 * @date 2023/11/06
 */
@FeignClient("company")
public interface CompanyClient {
    /**
     * 获取企业信息
     * @param weId weid
     * @return 返回企业信息
     */
    @GetMapping("/company/{weId}")
    CommonResult<CompanyDTO> getCompanyMessage(@PathVariable String weId);
}
