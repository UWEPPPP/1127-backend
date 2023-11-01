package com.topview.service;

import com.topview.bo.ChainServiceBO;
import org.fisco.bcos.sdk.v3.codec.ContractCodecException;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionBaseException;

import java.util.Map;

/**
 * chain service
 *
 * @author 刘家辉
 * @date 2023/11/01
 */
public interface ChainService {
    /**
     * call
     *
     * @param chainServiceBO chain service bo
     * @return {@link Map}<{@link String}, {@link Object}>
     * @throws TransactionBaseException transaction base exception
     * @throws ContractCodecException   contract codec exception
     */
    Map<String, Object> call(ChainServiceBO chainServiceBO) throws TransactionBaseException, ContractCodecException;

    /**
     * send
     *
     * @param chainServiceBO chain service bo
     * @return {@link Map}<{@link String}, {@link Object}>
     * @throws TransactionBaseException transaction base exception
     * @throws ContractCodecException   contract codec exception
     */
    Map<String, Object> send(ChainServiceBO chainServiceBO) throws TransactionBaseException, ContractCodecException;
}
