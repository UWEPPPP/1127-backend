package www.topview.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import www.topview.dto.ChainServiceDTO;
import www.topview.dto.CreateProcessorDTO;
import www.topview.result.CommonResult;

@FeignClient("1127-chain")
public interface ChainClient {
    @GetMapping("/chain")
    CommonResult<Object> call(ChainServiceDTO chainServiceDTO);

    @PostMapping("/chain")
    CommonResult<Object> send(ChainServiceDTO chainServiceDTO);

    @PostMapping("processor")
    CommonResult<Void> create(CreateProcessorDTO createProcessorDTO);
}
