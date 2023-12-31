import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import www.topview.entity.model.AccountModel;
import www.topview.exception.WeIdentityException;
import www.topview.service.DomainService;
import www.topview.service.WeIdentityService;

@SpringBootTest(classes = {www.topview.RoleApplication.class})
@RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@Slf4j
public class WeIdentityTest {
    @Autowired
    WeIdentityService weIdentityService;
    @Autowired
    DomainService domainService;

    @org.junit.Test
    public void test() throws WeIdentityException {
//        KeyPair rsa = SecureUtil.generateKeyPair("RSA");
//        FileWriter fileWriter = new FileWriter("private_key.txt");
//        FileWriter fileWriter1 = new FileWriter("public_key.txt");
//        fileWriter.write(Base64.encode(rsa.getPrivate().getEncoded()));
//        fileWriter1.write(Base64.encode(rsa.getPublic().getEncoded()));
//        String encrypt = CryptoUtil.encrypt("123", PathConstant.PATH_PUBLIC_KEY);
//        String decrypt = CryptoUtil.decrypt(encrypt, PathConstant.PATH_PRIVATE_KEY);
//        assert decrypt.equals("123");
//        System.out.printf(StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));
        for (int i = 0; i < 10; i++) {
            System.out.println("账户" + i);
            AccountModel weId = weIdentityService.createWeId();
            System.out.println(weId.getWeId());
            System.out.println(weId.getAccountAddress());
            System.out.println(weId.getPrivateKey());
            System.out.println(weId.getPublicKey());
        }
    }

    @Test
    public void createWeId() throws WeIdentityException {
//        try {
//            System.out.printf(weIdentityService.createWeId().toString());
//        } catch (WeIdentityException e) {
//            log.error("WeIdentity调用异常", e);
//        }
        AccountModel weId = weIdentityService.createWeId();
        System.out.println(weId.getPublicKey());
        System.out.println(weId.getWeId());
        System.out.println(weId.getAccountAddress());
        System.out.println(weId.getPrivateKey());

    }

    @org.junit.Test
    public void registerAuthorityIssuer() {
        try {
            Boolean 草草草 = weIdentityService.registerAuthorityIssuer(weIdentityService.createWeId().getWeId(), "草草草");
            System.out.printf(String.valueOf(草草草));
        } catch (WeIdentityException e) {
            log.error("WeIdentity调用异常", e);
        }
    }

    @org.junit.Test
    public void getCptTemplate() {
        String s = domainService.getCptTemplate();
        System.out.printf(s);
    }


}
