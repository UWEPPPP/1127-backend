package com.topview.service;

import com.topview.bo.CreateProcessorBO;

import java.io.IOException;

public interface ProcessorService {
    /**
     * 创建处理器
     *
     * @param createProcessorBO 创造处理器bo
     * @throws IOException ioexception
     */
    void createProcessor(CreateProcessorBO createProcessorBO) throws IOException;
}
