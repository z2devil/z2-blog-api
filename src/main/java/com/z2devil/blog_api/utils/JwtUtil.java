package com.z2devil.blog_api.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.z2devil.blog_api.config.bean.AuthProperties;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: blog_api
 * @description: token 工具类
 * @author: z2devil
 * @create: 2021-05-22
 **/
public class JwtUtil {

    /**
     * JWT生成Token
     * @params [user_id, email]
     * @return String
     * @author z2devil
     * @date 2021/9/24
     */
    public static String createToken(int user_id, String email) {
        // 获取authProperties配置类
        AuthProperties authProperties = SpringContextHolder.getBean(AuthProperties.class);
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, authProperties.getTokenExpireTime());
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>(2);
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String token = JWT.create().withHeader(map)
                .withClaim("iss", "Service")
                .withClaim("aud", "APP")
                .withClaim("user_id", user_id)
                .withClaim("email", email)
                // sign time
                .withIssuedAt(iatDate)
                // expire time
                .withExpiresAt(expiresDate)
                // signature
                .sign(Algorithm.HMAC256(authProperties.getSecret()));

        return token;
    }

    /**
     * 验证token
     * @params [token]
     * @return Map<String,Claim>
     * @author z2devil
     * @date 2021/9/24
     */
    private static Map<String, Claim> verifyToken(String token) {
        // 获取authProperties配置类
        AuthProperties authProperties = SpringContextHolder.getBean(AuthProperties.class);
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(authProperties.getSecret())).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // token 校验失败, 抛出Token验证非法异常
            e.printStackTrace();
            return null;
        }
        return jwt.getClaims();
    }

    /**
     * 根据token获取id
     * @params [token]
     * @return int
     * @author z2devil
     * @date 2021/9/24
     */
    public static Integer getUid(String token) {
        Map<String, Claim> claims = verifyToken(token);
        if(claims != null) {
            Claim userIdClaim = claims.get("user_id");
            if (userIdClaim != null && userIdClaim.asInt() != 0) {
                return userIdClaim.asInt();
            }
        }
        return 0;
    }

    /**
     * 根据token获取email
     * @params [token]
     * @return String
     * @author z2devil
     * @date 2021/9/24
     */
    public static String getEmail(String token) {
        Map<String, Claim> claims = verifyToken(token);
        if(claims != null) {
            Claim usernameClaim = claims.get("email");
            if (usernameClaim != null && StrUtil.isNotBlank(usernameClaim.asString())) {
                return usernameClaim.asString();
            }
        }
        return "";
    }

    /**
     * 检查token是否在有效期
     * @params [token]
     * @return boolean
     * @author z2devil
     * @date 2021/9/24
     */
    public static boolean isExpiration(String token) {
        Map<String, Claim> claims = verifyToken(token);
        if(claims != null) {
            Claim expClaim = claims.get("exp");
            if (expClaim != null) {
                Date expDate = claims.get("exp").asDate();
                Date nowDate = new Date();
                return expDate.after(nowDate);
            }
        }
        return false;
    }

}
