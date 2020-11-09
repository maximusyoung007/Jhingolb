package com.maximus.jhingolbback.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
//获取配置文件的内容
@ConfigurationProperties(prefix = "jwt")
public class JWTUtil {
    private final static Logger log = LogManager.getLogger(JWTUtil.class);

    //过期时长
    @Setter @Getter private static long expire;
    //密钥
    @Setter @Getter private static String secret;

    //校验token是否正确
    public static boolean vertify(String token,String username,String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username",username).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //获得token中的信息
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            log.error(e);
            return null;
        }
    }

    /**
     * 生成签名
     * @param username
     * @param password
     * @return
     */
    public static String sign(String username,String password) {
        try {
            Date date = new Date(System.currentTimeMillis() + expire * 1000);
            Algorithm algorithm = Algorithm.HMAC256(password);
            return JWT.create()
                    .withClaim("username",username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }


}
