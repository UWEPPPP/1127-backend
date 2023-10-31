package www.topview.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
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


    public static Boolean getRsaKey(String publicKeyPath, String privateKeyPath) {
        KeyPair rsa = SecureUtil.generateKeyPair("RSA");
        FileWriter fileWriter = new FileWriter(privateKeyPath);
        FileWriter fileWriter1 = new FileWriter(publicKeyPath);
        fileWriter.write(Base64.encode(rsa.getPrivate().getEncoded()));
        fileWriter1.write(Base64.encode(rsa.getPublic().getEncoded()));
        return true;
    }

    public static String encrypt(String key, String pathPublicKey) {
        FileReader fileReader = new FileReader(pathPublicKey);
        String publicKey = fileReader.readString();
        RSA rsa = new RSA(null, Base64.decode(publicKey));
        return Base64.encode(rsa.encrypt(StrUtil.bytes(key, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey));
    }

    public static String decrypt(String key, String pathPrivateKey) {
        byte[] decode = Base64.decode(key);
        FileReader fileReader = new FileReader(pathPrivateKey);
        String privateKey = fileReader.readString();
        RSA rsa = new RSA(Base64.decode(privateKey), null);
        return StrUtil.str(rsa.decrypt(decode, KeyType.PrivateKey), CharsetUtil.CHARSET_UTF_8);
    }
}
