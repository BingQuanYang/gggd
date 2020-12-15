package com.smart.auth.jwt;

import cn.hutool.core.util.IdUtil;
import com.smart.auth.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 创建方法  jwt 字符串   json  web  token
 * 验证方法
 */
@Component
public class JwtUtils {
    @Resource
    JwtProperties jwtProperties;

    /**
     * @return
     */
    public String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                // 可选 默认已经实现  typ   jwt
//                .setHeader(heads)
                .addClaims(claims)
                // 回放攻击
                .setId(IdUtil.simpleUUID())
                // 签发的日期
                .setIssuedAt(new Date())
                // 设置过期时间
                .setExpiration(getExpire())
                //
                .setSubject("admin")
                //        SHA-256 签名
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                // 转化字符串
                .compact();
    }

    private Date getExpire() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, jwtProperties.getExpire());
        return calendar.getTime();
    }

    /**
     * @param token
     * @return
     */
    public Claims validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("12345")
                    .parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("认证失败");
        }
        return null;
    }


    /**
     * 40
     *
     * @param args
     */

    public static void main(String[] args) {
//        String token = createToken();
//        System.out.println(token);
//        Claims claims = validateToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsInVzZXJuYW1lIjoiYWRtaW4iLCJpYXQiOjE2MDgwMTI3NDgsImV4cCI6MTYwODAxMjc0OSwic3ViIjoiYWRtaW4ifQ.SXHxHJh26AFemEsGNBZM8sjdTetTjof0ptQHH3EjDdA");
//        String username = claims.get("username", String.class);
//        System.out.println(username);

    }


}
