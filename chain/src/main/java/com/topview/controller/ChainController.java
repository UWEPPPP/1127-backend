package com.topview.controller;

import com.topview.bo.ChainServiceBO;
import com.topview.config.ContractConfig;
import com.topview.service.ChainService;
import org.fisco.bcos.sdk.v3.codec.ContractCodecException;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionBaseException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.topview.dto.ChainServiceDTO;
import www.topview.result.CommonResult;

import javax.annotation.Resource;

@RestController
@RequestMapping("chain")
@ResponseBody
public class ChainController {

    @Resource
    private ContractConfig contractConfig;

    @Resource
    private ChainService chainService;

    @GetMapping
    public CommonResult<Object> call(@Validated ChainServiceDTO chainServiceDTO) throws TransactionBaseException, ContractCodecException {
        ChainServiceBO chainServiceBO = new ChainServiceBO();

        chainServiceBO.setUserId(chainServiceDTO.getUserId());
        chainServiceBO.setContractName(chainServiceDTO.getContractName());
        chainServiceBO.setContractAddress(contractConfig.contractName2Address(chainServiceDTO.getContractName()));
        chainServiceBO.setFunctionName(chainServiceDTO.getFunctionName());
        chainServiceBO.setFunctionParams(chainServiceDTO.getFunctionParams());

        return CommonResult.operateSuccess("调用" + chainServiceBO.getContractName() + "::" + chainServiceBO.getFunctionName() + "成功",
                chainService.call(chainServiceBO));
    }

    @PostMapping
    public CommonResult<Void> send(@Validated ChainServiceDTO chainServiceDTO) throws TransactionBaseException, ContractCodecException {
        ChainServiceBO chainServiceBO = new ChainServiceBO();

        chainServiceBO.setUserId(chainServiceDTO.getUserId());
        chainServiceBO.setContractName(chainServiceDTO.getContractName());
        chainServiceBO.setContractAddress(contractConfig.contractName2Address(chainServiceDTO.getContractName()));
        chainServiceBO.setFunctionName(chainServiceDTO.getFunctionName());
        chainServiceBO.setFunctionParams(chainServiceDTO.getFunctionParams());

        chainService.send(chainServiceBO);
        return CommonResult.operateSuccess("调用" + chainServiceBO.getContractName() + "::" + chainServiceBO.getFunctionName() + "成功");
    }
}
