package com.changgou.websearch.controller;

import com.changgou.common.entity.Page;
import com.changgou.search.feign.SkuFeign;
import com.changgou.search.pojo.SkuInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/12 10:00 下午
 */
@Controller
@RequestMapping("/search")
public class SkuController {
    @Autowired
    private SkuFeign skuFeign;

    /**
     * 搜索商品
     * @param searchMap 搜索条件
     * @return 查询结果集
     */
    @ApiOperation(value = "ES搜索商品", notes = "ES条件搜索商品", tags = {"SkuController"})
    @ApiImplicitParam(paramType = "param", name = "searchMap", value = "条件集合", dataType = "Map")
    @GetMapping("/list")
    public String search(@RequestParam(required = false) Map<String, String> searchMap, Model model) {
        Map<String, Object> resultMap = skuFeign.search(searchMap);
        model.addAttribute("result", resultMap);
        // 计算分页
        Page<SkuInfo> pageInfo = new Page<>(
                Long.parseLong(searchMap.get("total").toString()),
                Integer.parseInt(searchMap.get("pageNum").toString()) + 1,
                Integer.parseInt(searchMap.get("pageSize").toString())
        );
        model.addAttribute("pageInfo", pageInfo);
        // 将条件存储, 用于页面回显数据
        model.addAttribute("searchMap", searchMap);
        String[] urls = this.getUrl(searchMap);
        model.addAttribute("url", urls[0]);
        model.addAttribute("sortUrl", urls[1]);
        return "search";
    }

    /**
     * 获取上次请求地址
     * @return 组装后的url
     */
    public String[] getUrl(Map<String, String> searchMap) {
        String url = "/search/list";
        String sortUrl = "/search/list";
        if (searchMap != null && searchMap.size() > 0) {
            url += "?";
            sortUrl += "?";
            for (Map.Entry<String, String> entry : searchMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                // 跳过分页参数
                if ("pageNum".equalsIgnoreCase(key)) {
                    continue;
                }
                url += key + "=" + value + "&";
                // 跳过排序参数
                if ("sortField".equalsIgnoreCase(key) || "sortRule".equalsIgnoreCase(key)) {
                    continue;
                }
                sortUrl += key + "=" + value + "&";
            }
            // 去掉最后一个&
            url = url.substring(0, url.length() - 1);
            sortUrl = sortUrl.substring(0, url.length() - 1);
        }
        return new String[]{url, sortUrl};
    }

}
