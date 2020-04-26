package com.changgou.pay;

import com.changgou.common.util.HttpClient;
import org.junit.Test;

import java.io.IOException;

/**
 * Demo HttpClient使用案例
 *
 * @author lishiquan
 * @date 2020/4/23 4:26 下午
 */
public class HttpClientTest {
    /**
     * 发送Http或者Https请求
     */
    @Test
    public void testRequest() throws IOException {
        String url = "https://api.mch.weixin.qq.com/pay/orderquery";
        HttpClient httpClient = new HttpClient(url);
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<xml>\n" +
                "<money>998</money>\n" +
                "<sign>6CBA15EF4C0536FC6B8AFC6322896A9D</sign>\n" +
                "<id>1</id>\n" +
                "<title>畅购</title>\n" +
                "</xml>";
        // 设置xml参数
        httpClient.setXmlParam(xml);
        // 设置https
        httpClient.setHttps(true);
        // 设置请求方式
        httpClient.post();
        // 获取响应数据
        String content = httpClient.getContent();
        System.out.println(content);
    }
}
