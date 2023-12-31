package www.topview.result;

/**
 * @author 刘家辉
 * @date 2023/10/28
 */
public class ResultCode {
    public static final Integer SUCCESS_CODE = 200;
    public static final Integer FAIL_CODE = 400;

    /**
     * token非法错误码
     */
    public static final Integer TOKEN_INVALID_CODE = 401;
    /**
     * token过期错误码
     */
    public static final Integer TOKEN_EXPIRED_CODE = 402;
}
