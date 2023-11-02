import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import www.topview.constant.WebSecurityConstant;
import www.topview.util.JwtUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :Lictory
 * @date : 2023/11/01
 */
public class JwtTest {
    public static void main(String[] args) throws IOException {
        //admin_key_0x720fa5d41557624e24ba9771266de383e2b8f47c.pem
        //
        Map<String,Object> headers=new HashMap<>();
        headers.put("head","test");
        Map<String,Object> payloads=new HashMap<>();
        payloads.put("payload","test");


        byte[] key = Files.newInputStream(Paths.get("common/src/main/resources/test_private_key.txt")).readAllBytes();
        JWTSigner jwtSigner = JWTSignerUtil.createSigner("HMD5", key);


        String token = JWT.create()
                .setIssuer(WebSecurityConstant.ISSUER)
                .setSubject("subject")
                .setPayload("payload", payloads)
                .setExpiresAt(new Date(System.currentTimeMillis() + WebSecurityConstant.EXPIRE_TIME))
                .sign(jwtSigner);
        System.out.println(token);
    }

}
