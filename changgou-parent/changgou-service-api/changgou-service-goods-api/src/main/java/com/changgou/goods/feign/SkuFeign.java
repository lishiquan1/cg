package com.changgou.goods.feign;

import com.changgou.common.entity.Result;
import com.changgou.goods.pojo.Sku;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Sku Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name="goods") // 指定调用goods微服务
@RequestMapping("/sku")
public interface SkuFeign {

    /**
     * 查询Sku全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<Sku>> findAll();

    /**
     * 根据ID查询Sku数据
     * @param id Sku id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<Sku> findById(@PathVariable Integer id);

    /**
     * 新增Sku数据
     * @param sku Sku实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody Sku sku);

    /**
     * 修改Sku数据
     * @param sku Sku实体类
     * @param id Sku id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Sku sku, @PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id Sku id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<Sku> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param sku 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<Sku>> findList(@RequestBody(required = false) Sku sku);

    /**
     * Sku分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Sku>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * Sku条件搜索 + 分页
     * @param sku 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Sku>> findPage(@RequestBody(required = false) Sku sku, @PathVariable Integer page, @PathVariable Integer size);
}