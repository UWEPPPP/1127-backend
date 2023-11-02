package www.topview.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import www.topview.dto.CreateProcessorDTO;
import www.topview.result.CommonResult;

import java.io.IOException;

/**
 * chain service
 *
 * @author 刘家辉
 * @date 2023/11/02
 */
@FeignClient("1127-chain")
public interface ChainService {
    @PostMapping("/processor")
    public CommonResult<Void> create(@Validated CreateProcessorDTO createProcessorDTO) throws IOException;
}
