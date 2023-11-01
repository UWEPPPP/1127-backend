import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import www.topview.util.JwtUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        byte[] key = Files.newInputStream(Paths.get("common/src/main/resources/test_private_key.txt")).readAllBytes();
        Map<String,Object> headers=new HashMap<>();
        headers.put("head","test");
        Map<String,Object> payloads=new HashMap<>();
        payloads.put("payload","test");
        String jwtToken = JWTUtil.createToken(headers, payloads,new byte[0]);
        System.out.println(jwtToken);
    }

}
