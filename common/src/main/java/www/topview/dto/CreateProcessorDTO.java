package www.topview.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 创建 processor DTO
 *
 * @author ashinnotfound
 * @date 2023/10/30
 */
@Data
public class CreateProcessorDTO {
    /**
     * 用户 ID
     */
    @NotNull
    private Integer userId;
    /**
     * 私钥
     */
    @NotBlank
    private String privateKey;
}
