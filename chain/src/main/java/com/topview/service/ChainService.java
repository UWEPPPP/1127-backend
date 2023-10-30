package com.topview.service;

import com.topview.bo.ChainServiceBO;
import org.fisco.bcos.sdk.v3.codec.ContractCodecException;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionBaseException;

import java.util.Map;

public interface ChainService {
    Map<String, Object> call(ChainServiceBO chainServiceBO) throws TransactionBaseException, ContractCodecException;

    void send(ChainServiceBO chainServiceBO) throws TransactionBaseException, ContractCodecException;
}
