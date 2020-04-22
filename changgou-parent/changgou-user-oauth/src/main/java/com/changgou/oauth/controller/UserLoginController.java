package com.changgou.oauth.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.oauth.service.UserLoginService;
import com.changgou.oauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/17 10:39 上午
 */
@RestController
@RequestMapping("/oauth")
@CrossOrigin
public class UserLoginController {
    // 客户端id
    @Value("${oauth.clientId}")
    private String clientId;
    // 客户端秘钥
    @Value("${oauth.clientSecret}")
    private String clientSecret;
    @Autowired
    private UserLoginService userLoginService;

    /**
     * 登录方法
     * @param username 账号
     * @param password 密码
     * @return 登录结果
     */
    @PostMapping(value = "/login")
    public Result<AuthToken> login(String username, String password) {
        String grantType = "password";
        AuthToken authToken = userLoginService.login(username, password, clientId, clientSecret, grantType);
        if (authToken != null) {
            return new Result<>(true, StatusCode.OK, "登录成功", authToken);
        }
        return new Result<>(false, StatusCode.LOGINERROR, "登录失败, 用户名或密码错误");
    }
}
