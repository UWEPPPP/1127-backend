package com.topview.service.impl;

import com.topview.client.ProcessorPool;
import com.topview.service.ChainService;
import com.topview.util.ContractUtil;
import org.fisco.bcos.sdk.v3.codec.ContractCodecException;
import org.fisco.bcos.sdk.v3.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.v3.transaction.model.dto.TransactionResponse;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionBaseException;
import org.springframework.stereotype.Service;
import com.topview.bo.ChainServiceBO;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 链端服务实现
 *
 * @author ashinnotfound
 * @date 2023/10/30
 */
@Service
public class ChainServiceImpl implements ChainService {

    @Resource
    private ProcessorPool processorPool;

    /**
     * 查询
     *
     * @param chainServiceBO 链服务bo
     * @return {@code Map<String, Object>}
     * @throws ContractCodecException   合约编解码器异常
     * @throws TransactionBaseException 事务基础异常
     */
    @Override
    public Map<String, Object> call(ChainServiceBO chainServiceBO) throws TransactionBaseException, ContractCodecException {
        CallResponse callResponse = processorPool.getProcessor(chainServiceBO.getUserId())
                .sendCallByContractLoader(chainServiceBO.getContractName(), chainServiceBO.getContractAddress(), chainServiceBO.getFunctionName(), chainServiceBO.getFunctionParams());

        return ContractUtil.decodeReturnStruct(callResponse.getReturnABIObject());
    }

    /**
     * 发送交易
     *
     * @param chainServiceBO 链服务bo
     * @return {@code Map<String, Object>}
     * @throws ContractCodecException   合约编解码器异常
     * @throws TransactionBaseException 事务基础异常
     */
    @Override
    public Map<String, Object> send(ChainServiceBO chainServiceBO) throws TransactionBaseException, ContractCodecException {
        TransactionResponse transactionResponse = processorPool.getProcessor(chainServiceBO.getUserId()).sendTransactionAndGetResponseByContractLoader(
                chainServiceBO.getContractName(), chainServiceBO.getContractAddress(), chainServiceBO.getFunctionName(), chainServiceBO.getFunctionParams());
        Assert.isTrue(transactionResponse.getTransactionReceipt().isStatusOK(), transactionResponse.getReceiptMessages());
        return ContractUtil.decodeReturnStruct(transactionResponse.getReturnABIObject());
    }
}
