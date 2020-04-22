package com.changgou.search.dao;

import com.changgou.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/10 11:29 上午
 */
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo, Integer> {
}
