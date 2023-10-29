package www.topview.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import www.topview.exception.WeIdentityException;
import www.topview.result.CommonResult;

/**
 * @author 刘家辉
 * @date 2023/10/29
 */
@ControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(WeIdentityException.class)
    public CommonResult<Void> handlerWeIdentityException(WeIdentityException ex){
        log.error("IllegalArgumentException", ex);
        return CommonResult.operateFailWithMessage(ex.getMessage());
    }
}
