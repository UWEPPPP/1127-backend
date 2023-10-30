package www.topview.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import www.topview.dto.CreateProcessorDTO;
import www.topview.result.CommonResult;

@FeignClient
@RequestMapping("processor")
public interface ProcessorClient {

    @PostMapping
    CommonResult<Void> create(CreateProcessorDTO createProcessorDTO);
}
