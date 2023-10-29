package www.topview.result;

import lombok.Data;
import www.topview.constant.ResultCode;

/**
 * @author :Lictory
 * @date : 2023/10/27
 */

@Data
public class CommonResult<T> {

    Integer code;
    String message;
    boolean isSuccess;
    Object data;

    public CommonResult(Integer code, String message, boolean isSuccess, Object data) {
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public CommonResult(Integer code, String message, boolean isSuccess) {
        this.code = code;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public static CommonResult<Void> operateSuccess(String message, boolean bool){
        return new CommonResult<>(ResultCode.SUCCESS_CODE,message,bool);
    }

}
