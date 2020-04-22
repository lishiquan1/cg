package com.changgou.oauth.service;

import com.changgou.oauth.util.AuthToken;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/17 11:01 上午
 */
public interface UserLoginService {
    /**
     * 密码模式登录
     * @param username 用户名
     * @param password 密码
     * @param clientId 客户端id
     * @param clientSecret 秘钥
     * @param grantType 授权方式
     */
    AuthToken login(String username, String password, String clientId, String clientSecret, String grantType);
}