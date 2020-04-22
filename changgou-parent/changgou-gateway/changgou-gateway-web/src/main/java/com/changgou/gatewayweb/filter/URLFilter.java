package com.changgou.gatewayweb.filter;

/**
 * Demo 不需要认证就能访问的路径认证
 *
 * @author lishiquan
 * @date 2020/4/19 11:12 下午
 */
public class URLFilter {
    private static final String allurl = "/oauth/login,/user/add";
    /**
     * 校验当前访问路径是否需要验证权限
     * 如果不需要验证: false
     * 如果需要验证: true
     * @param url 访问路径
     * @return boolean
     */
    public static boolean hasAuthorize(String url){
        String[] urls = allurl.split(",");
        for (String uri : urls) {
            if (uri.equals(url)){
                return false;
            }
        }
        return true;
    }
}
