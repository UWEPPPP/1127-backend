package www.topview.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 链服务 DTO
 *
 * @author ashinnotfound
 * @date 2023/10/30
 */
@Data
@Accessors(chain = true)
public class ChainServiceDTO {
    /**
     * 用户 ID
     * 0为admin
     */
    @NotNull
    private Integer userId;
    /**
     * 合约名
     */
    @NotBlank
    private String contractName;
    /**
     * 合约地址
     */
    @NotBlank
    private String contractAddress;
    /**
     * 函数名称
     */
    @NotBlank
    private String functionName;
    /**
     * 函数参数
     */
    private List<Object> functionParams;
}
