package www.topview.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.springframework.stereotype.Component;
import www.topview.constant.WebSecurityConstant;
import www.topview.dto.PayLoad;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @author :Lictory
 * @date : 2023/10/31
 */

@Component
public class JwtUtil {
    private static final byte[] KEY = "这是一个key".getBytes();
    private static JWTSigner jwtSigner;
    private static final String HEADER = "token";
    private static final String PAYLOAD_NAME = "payload";

    @PostConstruct
    public void init() throws IOException {
        //TODO 设置指定加密算法
        jwtSigner = JWTSignerUtil.createSigner("HMD5", KEY);

    }

    public static Integer getUserId(HttpServletRequest request) {
        JWT jwt = JWT.of(request.getHeader(HEADER));
        PayLoad payload = (PayLoad) jwt.getPayload(PAYLOAD_NAME);
        return payload.getUserId();
    }

    public String createJwtToken(String subject, Object payload) {
        String token;
        try {
            token = JWT.create()
                    .setIssuer(WebSecurityConstant.ISSUER)
                    .setKey(KEY)
                    .setSubject(subject)
                    .setPayload("payload", payload)
                    .setExpiresAt(new Date(System.currentTimeMillis() + WebSecurityConstant.EXPIRE_TIME))
                    .sign(jwtSigner);
        } catch (JWTException e) {
            throw new JWTException("jwt生成异常");
        }

        return token;

    }

    public boolean validateToken(String jwt) {
        return validateDate(jwt) || validateSignature(jwt);
    }

    public boolean validateSignature(String token) {
        //验证签名算法
        boolean validSignature;
        JWT jwt = JWT.of(token);
        try {
            //验证JWT的有效性跟签名
            validSignature = jwt.setKey(KEY).verify(jwtSigner);
        } catch (JWTException e) {
            throw new JWTException("jwt验证签名发生异常");
        }
        return validSignature;
    }


    public boolean validateDate(String token) {
        boolean validateDate = true;
        try {
            return token != null && JWT.of(token).setKey(WebSecurityConstant.SIGN_KEY.getBytes()).validate(0);
        } catch (JWTException e) {
            return false;
        }
    }

}
