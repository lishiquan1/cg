package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Demo Brand持久层接口
 *
 * @author lishiquan
 */
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 根据分类id查询品牌集合
     * @param cid Category分类id
     * @return 品牌集合
     */
    @Select("select tb.* from tb_brand tb inner join tb_category_brand tcb on tb.id=tcb.brand_id where tcb.category_id=#{cid}")
    List<Brand> findByCategory(Integer cid);
}
