package com.topview.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateProcessorBO {
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
