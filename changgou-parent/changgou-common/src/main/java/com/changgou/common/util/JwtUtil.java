package com.changgou.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * Demo 生成令牌验证令牌工具类
 *
 * @author lishiquan
 * @date 2020/4/15 9:09 下午
 */
public class JwtUtil {
    // JWT令牌有效期, 一小时
    private static final Long JWT_TTL = 3600000L;
    // JWT秘钥
    private static final String JWT_KEY = "changgou";

    /**
     * 创建令牌
     * @param id 唯一表示
     * @param subject 主题
     * @param ttlMillis 有效期
     * @param loading 载荷
     * @return 加密后的密文
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        // 当前系统时间
        long nowMillis = System.currentTimeMillis();
        // 令牌签发时间
        Date now = new Date(nowMillis);
        // 如果令牌为null, 则默认有效期1小时
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        // 令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        // 指定签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成秘钥
        SecretKey secretKey = generalKey();
        // Jwts.builder: 构建JWT令牌的对象
        JwtBuilder builder = Jwts.builder()
                // 唯一的ID
                .setId(id)
                // 主题, 可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("admin")
                // 签发时间
                .setIssuedAt(now)
                // 签名算法及秘钥
                .signWith(signatureAlgorithm, secretKey)
                // 设置过期时间
                .setExpiration(expDate);
        return builder.compact();
    }

    /**
     * 生成加密 SecretKey
     * @return 秘钥
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * jwt解密
     * @param jwt 令牌
     * @return 解密后的数据
     * @throws Exception 异常
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
