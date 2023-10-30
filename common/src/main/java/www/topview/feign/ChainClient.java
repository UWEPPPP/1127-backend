package www.topview.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import www.topview.dto.ChainServiceDTO;
import www.topview.result.CommonResult;

@FeignClient
@RequestMapping("chain")
public interface ChainClient {
    @GetMapping
    CommonResult<Object> call(ChainServiceDTO chainServiceDTO);

    @PostMapping
    CommonResult<Object> send(ChainServiceDTO chainServiceDTO);
}
