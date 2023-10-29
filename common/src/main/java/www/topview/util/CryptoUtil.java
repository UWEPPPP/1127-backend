package www.topview.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.KeyPair;

/**
 * @author 刘家辉
 * @date 2023/10/29
 */
public class CryptoUtil {

    public static KeyPair getRandomKeyPair(){
        return SecureUtil.generateKeyPair("TopViewBlockChain");
    }

    public static String encrypt(String key,String publicKey){
        RSA rsa = new RSA();
        byte[] encrypt = rsa.encrypt(StrUtil.bytes(key, CharsetUtil.CHARSET_UTF_8), KeyType.valueOf(publicKey));
        return StrUtil.str(encrypt,CharsetUtil.CHARSET_UTF_8);
    }

    public static  String decrypt(String key,String privateKey){
        RSA rsa = new RSA();
        byte[] decrypt = rsa.decrypt(StrUtil.bytes(key, CharsetUtil.CHARSET_UTF_8), KeyType.valueOf(privateKey));
        return StrUtil.str(decrypt,CharsetUtil.CHARSET_UTF_8);
    }
}
