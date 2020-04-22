package com.changgou.common.util;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/19 11:39 下午
 */
public class TokenDecode {
    /**
     * 公钥
     */
    private static final String PUBLIC_KEY = "public.key";
    private static String publickey = "";

    /**
     * 获取非对称加密公钥
     * @return 公钥
     */
    public static String getPubKey(){
        if (!StringUtils.isEmpty(publickey)){
            return publickey;
        }
        Resource resource = new ClassPathResource(PUBLIC_KEY);
        try {
            InputStreamReader isr = new InputStreamReader(resource.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            publickey = br.lines().collect(Collectors.joining("\n"));
            return publickey;
        }catch (IOException e){
            return null;
        }
    }

    /**
     * 获取令牌信息
     * @param token
     * @return
     */
    public static Map<String, Object> decodeToken(String token){
        // 校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(getPubKey()));
        String claims = jwt.getClaims();
        return JSON.parseObject(claims, Map.class);
    }

    /**
     * 获取用户信息
     * @return
     */
    public static Map<String, Object> getUserInfo(){
        // 获取授权信息
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        // 获取token
        String token = details.getTokenValue();
        // 令牌解码
        return decodeToken(token);
    }
}
