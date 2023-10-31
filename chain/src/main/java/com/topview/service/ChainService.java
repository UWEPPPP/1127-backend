package com.topview.service;

import com.topview.bo.ChainServiceBO;
import org.fisco.bcos.sdk.v3.codec.ContractCodecException;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionBaseException;

import java.util.Map;

public interface ChainService {
    Map<String, Object> call(ChainServiceBO chainServiceBO) throws TransactionBaseException, ContractCodecException;

    Map<String, Object> send(ChainServiceBO chainServiceBO) throws TransactionBaseException, ContractCodecException;
}
