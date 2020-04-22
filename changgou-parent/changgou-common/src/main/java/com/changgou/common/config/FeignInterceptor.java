package com.changgou.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

/**
 * Demo 用户令牌
 *
 * @author lishiquan
 * @date 2020/4/19 2:41 下午
 */
public class FeignInterceptor implements RequestInterceptor {
    /**
     * Feign执行之前进行拦截
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获取用户的令牌
        // 记录了当前用户请求的所有数据, 包含请求头和请求参数等
        // 如果开启了熔断, 默认是线程池隔离, 会开启新的线程, 需要将熔断策略换成信号量隔离, 此时不会开启新的线程
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        /*
         * 获取请求头中的数据
         * 获取所有头的名字
         */
        Enumeration<String> headerNames = attributes.getRequest().getHeaderNames();
        while (headerNames.hasMoreElements()) {
            // 请求头的key
            String headerKey = headerNames.nextElement();
            // 获取请求头的值
            String headerValue = attributes.getRequest().getHeader(headerKey);
            // 将请求头信息封装到头中, 使用Feign调用时, 会传递给下个微服务
            requestTemplate.header(headerKey, headerValue);
        }
    }

}
