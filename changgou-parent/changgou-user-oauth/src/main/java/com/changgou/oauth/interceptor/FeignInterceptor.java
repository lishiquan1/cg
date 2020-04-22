package com.changgou.oauth.interceptor;

import com.changgou.oauth.util.AdminToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * Demo 拦截
 *
 * @author lishiquan
 * @date 2020/4/19 2:41 下午
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {
    /**
     * Feign执行之前进行拦截
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        /*
         * 1.没有令牌, Feign调用之前, 生成令牌(admin)
         * 2.Feign调用之前, 令牌急需要携带过去
         * 3.Feign调用之前, 令牌需要存放到Header文件中
         * 4.请求 -> Feign调用 -> 拦截器RequestInterceptor -> Feign调用之前执行拦截
         */
        // 生成管理员令牌
        String token = AdminToken.adminToken();
        // 将令牌放在请求头中
        requestTemplate.header("Authorization", "bearer " + token);

    }

}
