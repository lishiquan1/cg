package com.changgou.gatewayweb.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Demo 全局过滤器
 *
 * @author lishiquan
 * @date 2020/4/15 9:44 下午
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZE_TOKEN = "Authorization";

    /**
     * 全局过滤器, 实现用户权限校验
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 用户如果是登录或者是一些不需要权限认证的请求, 直接放行
        String uri = request.getURI().toString();
        if (!URLFilter.hasAuthorize(uri)) {
            return chain.filter(exchange);
        }
        // 获取用户令牌信息
        // 1.头文件中
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        // 判断令牌是否在头文件中
        boolean hasToken = true;
        // 2.参数获取令牌
        if (StringUtils.isEmpty(token)) {
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            hasToken = false;
        }
        // 3.Cookie中获取令牌
        if (StringUtils.isEmpty(token)) {
            HttpCookie httpCookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if (httpCookie != null) {
                token = httpCookie.getValue();
            }
        }
        // 获取用户令牌信息, 如果么有令牌, 则拦截
        if (StringUtils.isEmpty(token)) {
            // 设置没有权限的状态码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 响应空数据
            return response.setComplete();
        }
        // 判断令牌
        if (StringUtils.isEmpty(token)) {
            // 设置没有权限的状态码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 响应空数据
            return response.setComplete();

        } else {
            if (!hasToken) {
                if (!token.startsWith("bearer ") && !token.startsWith("Bearer ")) {
                    // 判断是否有前缀
                    token = "bearer " + token;
                }
                request.mutate().header(AUTHORIZE_TOKEN, token);
            }
        }
        // 有效则放行
        return chain.filter(exchange);
    }

    /**
     * 排序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
