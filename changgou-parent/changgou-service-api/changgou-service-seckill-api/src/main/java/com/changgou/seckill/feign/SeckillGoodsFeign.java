package com.changgou.seckill.feign;

import com.changgou.common.entity.Result;
import com.changgou.seckill.pojo.SeckillGoods;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Demo SeckillGoods Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "seckill")
@RequestMapping("/seckillGoods")
public interface SeckillGoodsFeign {

    /**
     * 查询SeckillGoods全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<SeckillGoods>> findAll();

    /**
     * 根据ID查询SeckillGoods数据
     * @param id SeckillGoods id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<SeckillGoods> findById(@PathVariable Integer id);

    /**
     * 新增SeckillGoods数据
     * @param seckillGoods SeckillGoods实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody SeckillGoods seckillGoods);

    /**
     * 修改SeckillGoods数据
     * @param seckillGoods SeckillGoods实体类
     * @param id SeckillGoods id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody SeckillGoods seckillGoods,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id SeckillGoods id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<SeckillGoods> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param seckillGoods 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<SeckillGoods>> findList(@RequestBody(required = false) SeckillGoods seckillGoods);

    /**
     * SeckillGoods分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<SeckillGoods>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * SeckillGoods条件搜索 + 分页
     * @param seckillGoods 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<SeckillGoods>> findPage(@RequestBody(required = false) SeckillGoods seckillGoods, @PathVariable Integer page, @PathVariable Integer size);
}