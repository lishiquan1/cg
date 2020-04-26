package com.changgou.goods.dao;

import com.changgou.goods.pojo.Sku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * Demo Sku持久层接口
 *
 * @author lishiquan
 */
public interface SkuMapper extends Mapper<Sku> {
    /**
     * 开启行级锁修改商品库存
     * @param id 商品id
     * @param num 递减的数量
     * @return 受影响的行
     * 需要绑定参数, 编译后参数会变成arg0, arg1
     */
    @Update("update tb_sku set num=num-#{num} where id=#{id} and num>=#{num}")
    Integer decrCount(@Param("id") Integer id,@Param("num") Integer num);
}
