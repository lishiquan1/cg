package com.changgou.oauth.service.impl;

import com.changgou.oauth.service.UserLoginService;
import com.changgou.oauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/17 11:01 上午
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    /**
     * 实现请求方式
     */
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 获取微服务
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 
     * @param username 用户名
     * @param password 密码
     * @param clientId 客户端id
     * @param clientSecret 秘钥
     * @param grantType 授权方式
     */
    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret, String grantType) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-oauth");
        String url = serviceInstance.getUri() + "/oauth/token";
        // 提交的数据(请求体)
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("username", username);
        bodyMap.add("password", password);
        bodyMap.add("grant_type", grantType);
        // 封装请求头
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
        byte[] code = Base64.getEncoder().encode((clientId + ":" + clientSecret).getBytes());
        String basic = new String(code, StandardCharsets.UTF_8);
        String authorization = "Basic " + basic;
        headerMap.add("Authorization", authorization);
        // 封装请求头和请求体
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(bodyMap, headerMap);
        // 发送请求, 获取授权信息
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        // 用户登录后的令牌信息
        Map<String, String> body = response.getBody();
        // 将令牌转换成AuthToken对象
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken(body.get("access_token"));
        authToken.setRefreshToken(body.get("refresh_token"));
        authToken.setJti(body.get("jti"));
        return authToken;
    }
}
