package www.topview.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.AlgorithmUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.springframework.stereotype.Component;
import www.topview.constant.WebSecurityConstant;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;

/**
 * @author :Lictory
 * @date : 2023/10/31
 */

@Component
public class JwtUtil {


    private static JWTSigner jwtSigner;
    private static byte[] key;

    @PostConstruct
    public void init() throws IOException {
        //TODO 设置指定加密算法     以及私钥路径
        key = Files.newInputStream(Paths.get("common/src/main/resources/test_private_key.txt")).readAllBytes();
        jwtSigner = JWTSignerUtil.createSigner("HMD5", key);

    }

    public static String createJwtToken(String subject, Object payload) {
        String token;
        try {
            token = JWT.create()
                    .setIssuer(WebSecurityConstant.ISSUER)
                    .setKey(key)
                    .setSubject(subject)
                    .setPayload("payload", payload)
                    .setExpiresAt(new Date(System.currentTimeMillis() + WebSecurityConstant.EXPIRE_TIME))
                    .sign(jwtSigner);
        } catch (JWTException e) {
            throw new JWTException("jwt生成异常");
        }

        return token;

    }

    public static boolean validateToken(JWT jwt) {
        return validateDate(jwt) || validateSignature(jwt);
    }

    public static boolean validateSignature(JWT jwt) {
        //验证签名算法
        boolean validSignature;
        try {
            //验证JWT的有效性跟签名
            validSignature = jwt.setKey(key).verify(jwtSigner);
        } catch (JWTException e) {
            throw new JWTException("jwt验证签名发生异常");
        }
        return validSignature;
    }


    public static boolean validateDate(JWT jwt) {
        boolean validateDate = true;
        try {
            Date exp = (Date) jwt.getPayload().getClaim("exp");
            if (exp.before(new Date())) {
                //证明token过期
                validateDate = false;
            }
        } catch (JWTException e) {
            throw new JWTException("校验token日期时发生异常");
        }

        return validateDate;
    }

}
