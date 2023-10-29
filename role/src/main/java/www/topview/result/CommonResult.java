package www.topview.result;

import lombok.Data;
import lombok.experimental.Accessors;
import www.topview.constant.ResultCode;

/**
 * 前后端通讯标准
 *
 * @author ashinnotfound
 * @date 2023/01/29
 */
@Data
@Accessors(chain = true)
public class CommonResult<T> {
    Integer code;

    boolean isSuccess;

    String message;

    Object data;

    public CommonResult(Integer code, boolean isSuccess, String message) {
        this.code = code;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public CommonResult(Integer code, boolean isSuccess, String message, T data) {
        this.code = code;
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public static CommonResult<Void> operateFailWithMessage(String message) {
        return new CommonResult<>(ResultCode.FAIL_CODE, false, message);
    }

    public static CommonResult<Void> operateFailDueToToken(Integer code, String message){
        return new CommonResult<>(code, false, message);
    }

    public static CommonResult<Void> operateSuccess(String message, Boolean bool) {
        return new CommonResult<>(ResultCode.SUCCESS_CODE, true, message);
    }

    public static <P> CommonResult<P> operateSuccess(String message, P data) {
        return new CommonResult<>(
                ResultCode.SUCCESS_CODE,
                true,
                message,
                data
        );
    }
}