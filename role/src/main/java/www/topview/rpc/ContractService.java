package www.topview.rpc;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import www.topview.dto.ChainServiceDTO;
import www.topview.result.CommonResult;

/**
 * contract service
 *
 * @author 刘家辉
 * @date 2023/10/30
 */
@FeignClient("1127-chain")
public interface ContractService {
    /**
     * call
     *
     * @param chainServiceDTO chain service dto
     * @return {@link CommonResult}<{@link Object}>
     */
    @GetMapping("/chain")
    public CommonResult<Object> call(ChainServiceDTO chainServiceDTO);

    /**
     * send
     *
     * @param chainServiceDTO chain service dto
     * @return {@link CommonResult}<{@link Object}>
     */
    @PostMapping("/chain")
    public CommonResult<Object> send(ChainServiceDTO chainServiceDTO);
}
