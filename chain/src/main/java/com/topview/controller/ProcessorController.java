package com.topview.controller;

import com.topview.bo.CreateProcessorBO;
import com.topview.service.ProcessorService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import www.topview.dto.CreateProcessorDTO;
import www.topview.result.CommonResult;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("processor")
@ResponseBody
public class ProcessorController {

    @Resource
    private ProcessorService processorService;

    @PostMapping
    public CommonResult<Void> create(@Validated CreateProcessorDTO createProcessorDTO) throws IOException {
        CreateProcessorBO createProcessorBO = new CreateProcessorBO();
        createProcessorBO.setUserId(createProcessorDTO.getUserId());
        createProcessorBO.setPrivateKey(createProcessorDTO.getPrivateKey());

        processorService.createProcessor(createProcessorBO);
        return CommonResult.operateSuccess("为用户(userId:" + createProcessorBO.getUserId() + ")创建processor成功");
    }
}
