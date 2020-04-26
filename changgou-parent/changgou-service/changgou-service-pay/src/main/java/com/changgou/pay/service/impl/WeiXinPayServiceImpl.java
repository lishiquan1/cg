package com.changgou.pay.service.impl;

import com.changgou.common.util.HttpClient;
import com.changgou.pay.service.WeiXinPayService;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/24 11:59 上午
 */
@Service
public class WeiXinPayServiceImpl implements WeiXinPayService {
    /**
     * 应用id
     */
    @Value("${weixin.appId}")
    private String appId;
    /**
     * 商户号
     */
    @Value("${weixin.partner}")
    private String partner;
    /**
     * 秘钥
     */
    @Value("${weixin.partnerKey}")
    private String partnerKey;
    /**
     * 支付回调地址
     */
    @Value("${weixin.notifyUrl}")
    private String notifyUrl;

    /**
     * 创建二维码
     * @param parameterMap 请求微信支付需要的参数
     * @return 微信支付返回的结果
     */
    @Override
    public Map<String, String> createNative(Map<String, String> parameterMap) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            // 应用id
            paramMap.put("appid", appId);
            // 商户id
            paramMap.put("mch_id", partner);
            // 随机字符串
            paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
            // 描述
            paramMap.put("body", "畅购商城");
            // 订单号
            paramMap.put("out_trade_no", parameterMap.get("outtradeno"));
            // 交易金额(单位: 分)
            paramMap.put("total_fee", parameterMap.get("totalfee"));
            // 终端IP
            paramMap.put("spbill_create_ip", "127.0.0.1");
            // 交易结果回调通知地址
            paramMap.put("notify_url", notifyUrl);
            // 交易类型
            paramMap.put("product_id", "NATIVE");
            // Map转XML并添加签名
            String xmlParameters = WXPayUtil.generateSignedXml(paramMap, partnerKey);
            // URL地址
            String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
            // 提交方式
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            // 提交参数
            httpClient.setXmlParam(xmlParameters);
            // 执行请求
            httpClient.post();
            // 获取返回的数据
            String resultXml = httpClient.getContent();
            // 将返回的XML数据转成Map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询微信支付状态
     * @param outTradeNo 订单号
     * @return
     */
    @Override
    public Map<String, String> queryStatus(String outTradeNo) {
        try {
            Map<String, String> paramMap = new HashMap<>();
            // 应用id
            paramMap.put("appid", appId);
            // 商户id
            paramMap.put("mch_id", partner);
            // 随机字符串
            paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
            // 订单号
            paramMap.put("out_trade_no", outTradeNo);
            // Map转XML并添加签名
            String xmlParameters = WXPayUtil.generateSignedXml(paramMap, partnerKey);
            // URL地址
            String url = "https://api.mch.weixin.qq.com/pay/orderquery";
            // 提交方式
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            // 提交参数
            httpClient.setXmlParam(xmlParameters);
            // 执行请求
            httpClient.post();
            // 获取返回的数据
            String resultXml = httpClient.getContent();
            // 将返回的XML数据转成Map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
