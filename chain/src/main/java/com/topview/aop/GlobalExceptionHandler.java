package com.topview.aop;

import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.codec.ContractCodecException;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionBaseException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import www.topview.result.CommonResult;

import java.util.Objects;

/**
 * 全局异常处理
 *
 * @author lql
 * @date 2023/02/12
 */
@ControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResult<Void> handleIllegalArgumentException(IllegalArgumentException ex){
        log.error("IllegalArgumentException", ex);
        return CommonResult.operateFailWithMessage(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public CommonResult<Void> handleRuntimeException(RuntimeException ex){
        log.error("RuntimeException", ex);
        return CommonResult.operateFailWithMessage(ex.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(TransactionBaseException.class)
    public CommonResult<Void> handleTransactionBaseException(TransactionBaseException ex){
        log.error("TransactionBaseException", ex);
        return CommonResult.operateFailWithMessage("查询合约内部信息异常：" + ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ContractCodecException.class)
    public CommonResult<Void> handleContractCodecExceptionException(ContractCodecException ex){
        log.error("ContractCodecException", ex);
        return CommonResult.operateFailWithMessage("查询合约内部信息异常：" + ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public CommonResult<Void> handleBindException(BindException ex){
        String errorMessage = "非法的参数: " + Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();
        log.error(errorMessage);
        return CommonResult.operateFailWithMessage(errorMessage);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResult<Void> handleException(Exception ex){
        log.error("Exception", ex);
        return CommonResult.operateFailWithMessage("系统繁忙，请稍后再试！" + ex.getMessage());
    }
}
