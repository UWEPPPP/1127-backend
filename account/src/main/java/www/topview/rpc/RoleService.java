package www.topview.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import www.topview.dto.AddCompanyDTO;
import www.topview.dto.AddWorkerDTO;
import www.topview.result.CommonResult;

/**
 * role service
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
@FeignClient("1127-role")
public interface RoleService {

    /**
     * add worker
     *
     * @param addWorkerDTO add worker dto
     * @return {@link CommonResult}<{@link Void}>
     */
    @PostMapping("/1127-role/companyAdmin/registerWorker")
    public CommonResult<Void> addWorker(AddWorkerDTO addWorkerDTO);

    /**
     * add company
     *
     * @param addCompanyDTO add company dto
     * @return {@link CommonResult}<{@link Void}>
     */
    @PostMapping("/1127-role/domainAdmin/addCompany")
    public CommonResult<Void> addCompany(AddCompanyDTO addCompanyDTO);
}
