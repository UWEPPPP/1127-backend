import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import www.topview.constant.PathConstant;
import www.topview.exception.WeIdentityException;
import www.topview.service.WeIdentityService;
import www.topview.util.CryptoUtil;

import java.security.KeyPair;

@SpringBootTest(classes = {www.topview.RoleApplication.class})
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@Slf4j
public class WeIdentityTest {
    @Autowired
    WeIdentityService weIdentityService;

    @org.junit.Test
    public void test(){
//        KeyPair rsa = SecureUtil.generateKeyPair("RSA");
//        FileWriter fileWriter = new FileWriter("private_key.txt");
//        FileWriter fileWriter1 = new FileWriter("public_key.txt");
//        fileWriter.write(Base64.encode(rsa.getPrivate().getEncoded()));
//        fileWriter1.write(Base64.encode(rsa.getPublic().getEncoded()));
        byte[] encrypt = CryptoUtil.encrypt("123", PathConstant.PATH_PUBLIC_KEY);
        String s = Base64.encode(encrypt);
        byte[] decode = Base64.decode(s);
        String decrypt = CryptoUtil.decrypt(decode, PathConstant.PATH_PRIVATE_KEY);
        assert decrypt.equals("123");
        System.out.printf(StrUtil.str(decrypt,CharsetUtil.CHARSET_UTF_8));

////获得私钥
//       System.out.printf(rsa.getPrivateKey().toString());
//        System.out.printf(rsa.getPrivateKeyBase64());
//
////获得公钥;
//        System.out.printf(rsa.getPublicKey().toString());
//        System.out.printf(rsa.getPublicKeyBase64());
//        new RSA(rsa.getPrivateKeyBase64(),rsa.getPublicKeyBase64());
////公钥加密，私钥解密
//        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
//        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
//
////Junit单元测试
////Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));
//
////私钥加密，公钥解密
//        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
//        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);

    }

    @org.junit.Test
    public void createWeId() {
        try {
           System.out.printf(weIdentityService.createWeId().toString());
        } catch (WeIdentityException e) {
           log.error("WeIdentity调用异常", e);
         }

    }
    @org.junit.Test
    public void registerAuthorityIssuer(){
        try {
            Boolean 草草草 = weIdentityService.registerAuthorityIssuer(weIdentityService.createWeId().getWeId(), "草草草");
            System.out.printf(String.valueOf(草草草));
        } catch (WeIdentityException e) {
            log.error("WeIdentity调用异常", e);
        }
    }



}
