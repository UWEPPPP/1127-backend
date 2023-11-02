package www.topview.constant;

/**
 * @author :Lictory
 * @date : 2023/11/01
 */
public class WebSecurityConstant {

    /**
     * token
     */
    public static final String TOKEN_HEADER = "token";
    /**
     * 到期时间(ms) 30min
     */
    public static final Integer EXPIRE_TIME = 30 * 60 * 1000;

    public static final String ISSUER = "TopView-BlockChain";
    public static final String SIGN_KEY = "TopView-BlockChain";


}
