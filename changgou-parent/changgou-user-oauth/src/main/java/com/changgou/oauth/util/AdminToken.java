package com.changgou.oauth.util;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/19 2:52 下午
 */
public class AdminToken {
    /**
     * 构建管理员令牌
     * @return token
     */
    public static String adminToken(){
        // 加载证书获取令牌
        ClassPathResource resource = new ClassPathResource("changgou.jks");
        // 加载读取证书数据
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, "changgou".toCharArray());
        // 获取证书中的一对秘钥
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("changgou", "changgou".toCharArray());
        // 获取私钥
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        // 创建令牌, 需要私钥加盐[RSA算法]
        Map<String, Object> payload = new HashMap<>();
        payload.put("nikename", "tomcat");
        payload.put("address", "sz");
        payload.put("authorities", new String[]{"admin", "oauth"});
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(payload), new RsaSigner(privateKey));
        return jwt.getEncoded();
    }


}
