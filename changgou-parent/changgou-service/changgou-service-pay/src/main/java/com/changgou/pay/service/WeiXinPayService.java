package com.changgou.pay.service;

import java.util.Map;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/24 5:24 下午
 */
public interface WeiXinPayService {
    /**
     * 创建二维码
     * @param parameterMap 请求微信支付需要的参数
     * @return 微信支付返回的结果
     */
    Map<String, String> createNative(Map<String, String> parameterMap);

    /**
     *
     * @param outTradeNo
     * @return
     */
    Map<String, String> queryStatus(String outTradeNo);
}
