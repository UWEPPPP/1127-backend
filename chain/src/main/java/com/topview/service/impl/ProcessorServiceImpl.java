package com.topview.service.impl;

import com.topview.bo.CreateProcessorBO;
import com.topview.client.ProcessorPool;
import com.topview.service.ProcessorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class ProcessorServiceImpl implements ProcessorService {
    @Resource
    private ProcessorPool processorPool;
    @Override
    public void createProcessor(CreateProcessorBO createProcessorBO) throws IOException {
        processorPool.createProcessor(createProcessorBO.getUserId(), createProcessorBO.getPrivateKey());
    }
}
