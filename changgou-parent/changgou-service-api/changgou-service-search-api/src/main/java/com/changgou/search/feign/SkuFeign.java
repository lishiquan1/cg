package com.changgou.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/12 9:55 下午
 */
@FeignClient(name = "search") // 调用search微服务
@RequestMapping("/search")
public interface SkuFeign {
    /**
     * 搜索商品
     * @param searchMap 搜索条件
     * @return 查询结果集
     */
    @GetMapping
    Map<String, Object> search(@RequestParam(required = false) Map<String, String> searchMap);
}

