package com.changgou.search.service;

import com.changgou.goods.pojo.Sku;

import java.util.Map;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/10 10:43 上午
 */
public interface SkuService {
    /**
     * 导入数据
     * @param sku 查询条件
     */
    void importData(Sku sku);

    /**
     * 多条件搜索
     * @param searchMap 条件
     * @return 查询结果
     */
    Map<String, Object> search(Map<String, String> searchMap);
}
