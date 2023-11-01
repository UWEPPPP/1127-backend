package www.topview.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
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

//    @PostConstruct
//    public void init() throws IOException {
//        //TODO 设置指定加密算法     以及私钥路径
//        byte[] key = Files.newInputStream(Paths.get("common/src/main/java/resource/test_private_key.txt")).readAllBytes();
//        algorithm = JWTSignerUtil.createSigner("HMD5", key);
//
//    }

    public static String createJwtToken(String subject,Object payload) {
        String token=JWT.create()
                .setIssuer(WebSecurityConstant.ISSUER)
                .setSubject(subject)
                .setPayload("payload",payload)
                .setExpiresAt(new Date(System.currentTimeMillis()+WebSecurityConstant.EXPIRE_TIME))
                .sign();
        return token;
    }


    public static JWT parseJwtToken(String token) {
        if(JwtUtil.verifyToken(token)){
            //token没过期
            return JWT.of(token);
        }else {
            //过期了
            return null;
        }
    }


    public static boolean verifyToken(String token){
        JWT decodeToken = JWT.of(token);
        Date expiresAt = (Date) decodeToken.getPayload("ExpiresAt");
        //证明当前token有没有过期
        if (expiresAt.compareTo(new Date(System.currentTimeMillis()+ WebSecurityConstant.EXPIRE_TIME))>0){
            //过期
            return false;
        }
        //没过期
        //TODO 决定一下要不要进行token的更新
        return true;
    }
}
