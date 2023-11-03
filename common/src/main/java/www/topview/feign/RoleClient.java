package www.topview.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import www.topview.dto.AddWorkerDTO;
import www.topview.result.CommonResult;

@FeignClient("1127-role")
public interface RoleClient {
    @PostMapping("companyAdmin/registerWorker")
    CommonResult<Void> addWorker(AddWorkerDTO addWorkerDTO);
}
