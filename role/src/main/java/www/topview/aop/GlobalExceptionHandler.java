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
    public CommonResult<Void> handlerWeIdentityException(WeIdentityException ex) {
        log.error("WeIdentity调用异常", ex);
        return CommonResult.operateFailWithMessage(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResult<Void> handlerIllegalArgumentException(IllegalArgumentException ex) {
        log.error("用户信息或数据库异常", ex);
        return CommonResult.operateFailWithMessage(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public CommonResult<Void> handlerRuntimeException(RuntimeException ex) {
        log.error("运行时异常", ex);
        return CommonResult.operateFailWithMessage("运行异常 请通知管理员");
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResult<Void> handlerException(Exception ex) {
        log.error("未知异常", ex);
        return CommonResult.operateFailWithMessage("系统繁忙 请稍后再试");
    }
}
