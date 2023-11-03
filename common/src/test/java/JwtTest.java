import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import www.topview.constant.WebSecurityConstant;
import www.topview.util.JwtUtil;

import javax.annotation.Resource;
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

@Slf4j
@SpringBootTest
public class JwtTest {


    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    public void test() throws IOException {
        jwtUtil.init();

        Map<String, Object> headers = new HashMap<>();
        headers.put("head", "test");
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("payload", "test");

        String token = jwtUtil.createJwtToken("1", payloads);

        System.out.println(token);
        System.out.println(jwtUtil.validateToken(token));
    }


}
