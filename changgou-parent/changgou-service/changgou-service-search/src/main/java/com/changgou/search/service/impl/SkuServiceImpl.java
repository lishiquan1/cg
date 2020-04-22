package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.common.entity.Result;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SkuEsMapper;
import com.changgou.search.service.SkuService;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.search.pojo.SkuInfo;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Demo 搜索
 *
 * @author lishiquan
 * @date 2020/4/10 10:45 上午
 */
@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuEsMapper skuEsMapper;

    /**
     * ElasticsearchTemplate 可以实现索引库的增删改查
     */
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 导入所有库
     * @param sku 查询条件
     */
    @Override
    public void importData(Sku sku) {
        // Feign调用, 查询所有Sku
        Result<List<Sku>> skuResult = skuFeign.findList(sku);
        // List<Sku>转成List<SkuInfo>
        List<SkuInfo> skuInfoList = JSON.parseArray(JSON.toJSONString(skuResult.getData()), SkuInfo.class);
        for (SkuInfo skuInfo : skuInfoList) {
            // 获取规格字符串, 转成Map集合
            Map<String, Object> map = JSON.parseObject(skuInfo.getSpec(), Map.class);
            // 将Map集合存入域中, 泛型是String和Object, 就会生成动态域, key为域名, value为属性值
            skuInfo.setSpecMap(map);
        }
        skuEsMapper.saveAll(skuInfoList);
    }

    /**
     * 多条件搜索
     * @param searchMap 条件
     * @return 查询结果
     */
    @Override
    public Map<String, Object> search(Map<String, String> searchMap) {
        // 获取生成器
        NativeSearchQueryBuilder nativeSearchQueryBuilder = builderBasicQuery(searchMap);
        // 获取查询结果集
        Map<String, Object> resultMap = this.searchMap(nativeSearchQueryBuilder);
        // 获取分类, 品牌, 规格参数
        Map<String, Object> searchParam = this.searchParam(nativeSearchQueryBuilder, searchMap);
        // 将参数添加到结果集中
        resultMap.putAll(searchParam);
        return resultMap;
    }

    /**
     * 生成器构造方法
     * @param searchMap 查询条件
     * @return 生成器
     */
    public NativeSearchQueryBuilder builderBasicQuery(Map<String, String> searchMap) {
        // 搜索条件构建对象, 用于封装搜索条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // BoolQueryBuilder 组合条件对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (searchMap != null && searchMap.size() > 0) {
            // 根据关键词搜索
            String keywords = searchMap.get("keywords");
            // 如果关键词不为空, 则搜索关键词数据
            if (!StringUtils.isEmpty(keywords)) {
                // nativeSearchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(keywords).field("name"));
                // 使用组合条件
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(keywords).field("name"));
            }
            // 分类搜索
            String category = searchMap.get("category");
            if (!StringUtils.isEmpty(category)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("categoryName", category));
            }
            // 品牌搜索
            String brand = searchMap.get("brand");
            if (!StringUtils.isEmpty(brand)) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandName", brand));
            }
            // 规格过滤
            for (Map.Entry<String, String> entry : searchMap.entrySet()) {
                String key = entry.getKey();
                if (key.startsWith("spec_")) {
                    // 条件
                    String value = entry.getValue();
                    // specMapNetwork.keyword 截取字符串
                    boolQueryBuilder.must(QueryBuilders.termQuery("specMap." + key.substring(5) + ".keyword", value));
                }
            }
            // 价格区间筛选
            String price = searchMap.get("price");
            if (!StringUtils.isEmpty(price)) {
                // 1.去掉中文
                price = price.replace("元", "").replace("以上", "");
                // 2.根据 - 分割
                String[] prices = price.split("-");
                // 3.查询
                if (prices.length > 0) {
                    // 价格大于
                    boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gt(Integer.parseInt(prices[0])));
                    if (prices.length == 2) {
                        // 价格小于等于
                        boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(Integer.parseInt(prices[1])));
                    }
                }
            }
            // 排序
            // 排序的域
            String sortField = searchMap.get("sortField");
            // 排序方式
            String sortRule = searchMap.get("sortRule");
            if (!StringUtils.isEmpty(sortField) && !StringUtils.isEmpty(sortRule)) {
                // 指定排序域
                nativeSearchQueryBuilder.withSort(new FieldSortBuilder(sortField)
                        // 指定排序规则
                        .order(SortOrder.valueOf(sortRule)));
            }
        }
        // 分页
        // 默认页码1
        Integer pageNum = coverterPage(searchMap);
        // 默认页大小
        Integer pageSize = 30;
        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageNum - 1, pageSize));
        // 将BoolQueryBuilder填充给nativeSearchQueryBuilder
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        return nativeSearchQueryBuilder;
    }

    /**
     * 关键字搜索方法
     * @param nativeSearchQueryBuilder 生成器
     * @return 结果集
     */
    public Map<String, Object> searchMap(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        // 高亮配置, 指定高亮域
        HighlightBuilder.Field field = new HighlightBuilder.Field("name");
        // 前缀
        field.preTags("<em style=\"color:red\">");
        // 后缀
        field.postTags("</em");
        // 碎片长度, 关键词数据的长度
        field.fragmentSize(100);
        // 添加高亮
        nativeSearchQueryBuilder.withHighlightFields(field);
        // 执行搜索, 获取结果集
        AggregatedPage<SkuInfo> page = elasticsearchTemplate
                .queryForPage(
                        // 搜索条件封装
                        nativeSearchQueryBuilder.build(),
                        // 数据集合要转换类型的字节码
                        SkuInfo.class,
                        // 执行搜索后, 将高亮数据结果集封装到该对象中
                        this.getSearchResultMapper()
                );
        // 获取搜索信息封装
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        Pageable pageable = query.getPageable();
        // 页码
        int pageNumber = pageable.getPageNumber();
        // 页大小
        int pageSize = pageable.getPageSize();
        // 分页参数-总记录数
        long totalElements = page.getTotalElements();
        // 总页数
        int totalPages = page.getTotalPages();
        // 获取数据结果集
        List<SkuInfo> content = page.getContent();
        // 封装数据并返回
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageNumber", pageNumber);
        resultMap.put("pageSize", pageSize);
        resultMap.put("rows", content);
        resultMap.put("total", totalElements);
        resultMap.put("totalPage", totalPages);

        return resultMap;
    }

    /**
     * 品牌, 分类, 规格封装
     * @param nativeSearchQueryBuilder 条件构造器
     * @param searchMap 条件
     * @return 参数结果集
     */
    public Map<String, Object> searchParam(NativeSearchQueryBuilder nativeSearchQueryBuilder, Map<String, String> searchMap) {
        Map<String, String> paramMap = new HashMap<>();
        // 当选择了分类作为搜索条件, 则不需要对分类进行搜索显示
        if (searchMap == null || StringUtils.isEmpty(searchMap.get("category"))) {
            // 获取分类集合
            paramMap.put("skuCategory", "categoryName");
        }
        // 当选择了品牌作为搜索条件, 则不需要对品牌进行搜索显示
        if (searchMap == null || StringUtils.isEmpty(searchMap.get("brand"))) {
            // 获取品牌集合
            paramMap.put("skuBrand", "BrandName");
        }
        // 获取规格集合
        paramMap.put("skuSpec", "spec.keyword");
        Map<String, List<String>> stringListMap = this.searchList(nativeSearchQueryBuilder, paramMap);
        // 筛选规格集合
        Map<String, Set<String>> specList = this.putAllSpec(stringListMap.get("specList"));
        // 结果集
        Map<String, Object> resultMap = new HashMap<>();
        stringListMap.remove("specList");
        resultMap.putAll(stringListMap);
        resultMap.put("specList", specList);
        return resultMap;
    }

    /**
     * 聚合方法抽取
     * @param nativeSearchQueryBuilder 生成器
     * @param paramMap 参数集合
     * @return 聚合集合
     */
    public Map<String, List<String>> searchList(NativeSearchQueryBuilder nativeSearchQueryBuilder, Map<String, String> paramMap) {
        Map<String, List<String>> resultMap = new HashMap<>();
        if (paramMap != null && paramMap.size() >= 1) {
            Set<Map.Entry<String, String>> entries = paramMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                /*
                 * 分组查询分组集合
                 * nativeSearchQueryBuilder.addAggregation() 添加一个聚合操作
                 * terms 取别名
                 * field 域名
                 * size 显示多少条数据
                 */
                String termsName = entry.getKey();
                String fieldName = entry.getValue();
                nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(termsName).field(fieldName).size(50));
            }
            // 执行搜索
            AggregatedPage<SkuInfo> aggregatedPage = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class);
            // 存入集合
            for (Map.Entry<String, String> entry : entries) {
                List<String> list = new ArrayList<>();
                /*
                 * 获取分组数据
                 * getAggregation() 获取聚合数据集合
                 * get("terms") 获取指定域集合数
                 */
                String termsName = entry.getKey();
                StringTerms stringTerms = aggregatedPage.getAggregations().get(termsName);
                for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
                    String name = bucket.getKeyAsString();
                    list.add(name);
                }
                resultMap.put(termsName.substring(3) + "List", list);
            }
        } else {
            resultMap.put("categoryList", null);
            resultMap.put("brandList", null);
            resultMap.put("specList", null);
        }
        // 返回集合
        return resultMap;
    }

    /**
     * 规格集合
     * @param specList 规格集合
     * @return 筛选后的规格集合
     */
    public Map<String, Set<String>> putAllSpec(List<String> specList) {
        Map<String, Set<String>> specAll = new HashMap<>();
        // 循环获取spec
        for (String spec : specList) {
            // 将spec字符串转换成map集合
            Map<String, String> map = JSON.parseObject(spec, Map.class);
            // 合并流程
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Set<String> set = specAll.get(key);
                // 如果没有规格就创建
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(value);
                specAll.put(key, set);
            }
        }
        return specAll;
    }

    /**
     * 获取页码
     * @param searchMap 查询条件
     * @return 页码
     */
    public Integer coverterPage(Map<String, String> searchMap) {
        if (searchMap != null && searchMap.size() > 0) {
            String pageNum = searchMap.get("pageNum");
            try {
                return Integer.parseInt(pageNum);
            } catch (NumberFormatException o) {
                o.printStackTrace();
            }
        }
        return 1;
    }

    /**
     * 高亮转换
     * @return 高亮数据集合
     */
    public SearchResultMapper getSearchResultMapper() {
        return new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                // 存储所有装换后的高亮数据
                List<T> list = new ArrayList<>();
                // 获取所有数据
                for (SearchHit hit : searchResponse.getHits()) {
                    // 分析结果集, 获取非高亮数据
                    SkuInfo skuInfo = JSON.parseObject(hit.getSourceAsString(), SkuInfo.class);
                    // 分析结果集, 获取高亮数据, 只有某个域的高亮数据
                    HighlightField highlightField = hit.getHighlightFields().get("name");
                    if (highlightField != null && highlightField.getFragments() != null) {
                        // 读取高亮数据
                        Text[] fragments = highlightField.getFragments();
                        StringBuilder buffer = new StringBuilder();
                        for (Text fragment : fragments) {
                            buffer.append(fragment);
                        }
                        // 将非高亮数据中指定域替换成高亮数据
                        skuInfo.setName(buffer.toString());
                    }
                    // 将高亮数据添加到集合中
                    list.add((T) skuInfo);
                }
                /*
                 * 将数据返回
                 * 1.List<T> content 搜索的结果集数据
                 * 2.pageable 分页对象信息
                 * 3.total 搜索的记录数量
                 */
                return new AggregatedPageImpl<>(list, pageable, searchResponse.getHits().getTotalHits());
            }
        };
    }
}
