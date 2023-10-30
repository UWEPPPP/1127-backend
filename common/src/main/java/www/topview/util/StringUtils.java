package www.topview.util;

/**
 * 字符串工具类
 *
 * @author lql
 * @date 2023/10/30
 */
public class StringUtils {
    public final static String EMPTY = "";

    /**
     * @param s 字符串
     * @return boolean 为空返回true
     */
    public static boolean isEmpty(String s){
        return s == null || s.equals(EMPTY);
    }

    /**
     * @param s 字符串
     * @return boolean 不为空返回true
     */
    public static boolean notEmpty(String s){return !isEmpty(s);}
}
