package com.topview.controller;

import com.topview.bo.CreateProcessorBO;
import com.topview.service.ProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.topview.dto.CreateProcessorDTO;
import www.topview.result.CommonResult;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("processor")
@ResponseBody
@Slf4j
public class ProcessorController {

    @Resource
    private ProcessorService processorService;

    @PostMapping
    public CommonResult<Void> create(@Validated @RequestBody CreateProcessorDTO createProcessorDTO) throws IOException {
        CreateProcessorBO createProcessorBO = new CreateProcessorBO();
        createProcessorBO.setUserId(createProcessorDTO.getUserId());
        createProcessorBO.setPrivateKey(createProcessorDTO.getPrivateKey());
        log.info(createProcessorBO.getPrivateKey());
        log.info(createProcessorBO.getUserId().toString());
        processorService.createProcessor(createProcessorBO);
        return CommonResult.operateSuccess("为用户(userId:" + createProcessorBO.getUserId().toString() + ")创建processor成功");
    }
}
