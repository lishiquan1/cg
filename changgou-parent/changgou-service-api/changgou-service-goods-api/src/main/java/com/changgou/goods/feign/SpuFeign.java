package com.changgou.goods.feign;

import com.changgou.common.entity.Result;
import com.changgou.goods.pojo.Spu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Demo
 *
 * @author lishiquan
 * @date 2020/4/19 11:00 上午
 */
@FeignClient(name = "goods")
@RequestMapping("/spu")
public interface SpuFeign {
    /**
     * 根据ID查询Spu数据
     * @param id Spu id
     * @return 查询成功
     */
    @GetMapping("/{id}")
    Result<Spu> findById(@PathVariable Integer id);

}
