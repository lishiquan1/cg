package com.changgou.pay.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.pay.service.WeiXinPayService;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/24 2:45 下午
 */
@RestController
@RequestMapping("/weixin/pay")
public class WeiXinPayController {
    @Autowired
    private WeiXinPayService weiXinPayService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 创建二维码
     * @param parameterMap 请求微信支付需要的参数
     * @return 二维码生成成功
     */
    @PostMapping("/create/native")
    public Result<Map<String, String>> createNative(@RequestBody Map<String, String> parameterMap) {
        // 创建二维码
        Map<String, String> qrCode = weiXinPayService.createNative(parameterMap);
        return new Result<>(true, StatusCode.OK, "二维码生成成功", qrCode);
    }

    /**
     * 查询微信支付状态
     * @param outTradeNo 订单号
     * @return
     */
    @PostMapping("/query/status")
    public Result<Map<String, String>> queryStatus(String outTradeNo) {
        Map<String, String> status = weiXinPayService.queryStatus(outTradeNo);
        return new Result<>(true, StatusCode.OK, "查询订单成功", status);
    }

    /**
     * 支付结果通知回调
     * @return
     */
    @RequestMapping("/notify/url")
    public String notifyUrl(HttpServletRequest request) throws Exception {
        // 获取网络输入流
        ServletInputStream is = request.getInputStream();
        // 创建byte字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 定义缓存区
        byte[] buffer = new byte[1024];
        int len = 0;
        // 读取网络输入流, 存储到字节输出流中
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        // 微信支付结果的字符数组
        byte[] bytes = baos.toByteArray();
        String xmlResult = new String(bytes, StandardCharsets.UTF_8);
        Map<String, String> resultMap = WXPayUtil.xmlToMap(xmlResult);
        // 发送支付结果给MQ
        rabbitTemplate.convertAndSend("exchange.order", "queue.order", JSON.toJSONString(resultMap));
        String result = "<xml>\n" +
                "\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
        return result;
    }

}
