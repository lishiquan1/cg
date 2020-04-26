package com.changgou.pay;

import com.github.wxpay.sdk.WXPayUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo 微信SDK测试
 *
 * @author lishiquan
 * @date 2020/4/23 4:02 下午
 */
public class WxPayUtilTest {
    /**
     * 随机生成字符
     */
    @Test
    public void testRandom() {
        String str = WXPayUtil.generateNonceStr();
        System.out.println(str);
    }

    /**
     * 将Map装成XML字符串
     */
    @Test
    public void testMapToXml() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("title", "畅购");
        map.put("money", "998");
        String xml = WXPayUtil.mapToXml(map);
        System.out.println(xml);
    }

    /**
     * 将Map装成XML字符串
     */
    @Test
    public void testMapToXmlAndSigner() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("title", "畅购");
        map.put("money", "998");
        String xml = WXPayUtil.mapToXml(map);
        // System.out.println(xml);
        String signerXml = WXPayUtil.generateSignedXml(map, "changgou");
        System.out.println(signerXml);
    }

    /**
     * 将XML字符串转成Map
     */
    @Test
    public void testXmlToMap() throws Exception {
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<xml>\n" +
                "<money>998</money>\n" +
                "<sign>6CBA15EF4C0536FC6B8AFC6322896A9D</sign>\n" +
                "<id>1</id>\n" +
                "<title>畅购</title>\n" +
                "</xml>";
        Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ":" + value);
        }
    }
}
