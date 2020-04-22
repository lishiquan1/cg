package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.service.SkuService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Sku表现层
 *
 * @author lishiquan
 */
@Api(value = "SkuController")
@RestController
@RequestMapping("/sku")
@CrossOrigin
public class SkuController {

    /**
     * sku业务层接口
     */
    @Autowired
    private SkuService skuService;

    /**
     * 查询Sku全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Sku",notes = "查询所Sku有方法详情",tags = {"SkuController"})
    @GetMapping
    public Result<List<Sku>> findAll(){
        // 调用SkuService实现查询所有Sku
        List<Sku> list = skuService.findAll();
        return new Result<List<Sku>>(true, StatusCode.OK, "查询成功", list) ;
    }

    /**
     * 根据ID查询Sku数据
     * @param id Sku id
     * @return 查询成功
     */
    @ApiOperation(value = "Sku根据ID查询",notes = "根据ID查询Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable Integer id){
        // 调用SkuService实现根据主键查询Sku
        Sku sku = skuService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", sku);
    }

    /**
     * 新增sku数据
     * @param sku Sku实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Sku添加",notes = "添加Sku方法详情",tags = {"SkuController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Sku对象",value = "传入JSON数据",required = true) Sku sku){
        // 调用SkuService实现添加Sku
        skuService.add(sku);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Sku数据
     * @param sku Sku实体类
     * @param id Sku id
     * @return 修改成功
     */
    @ApiOperation(value = "Sku根据ID修改",notes = "根据ID修改Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Sku对象",value = "传入JSON数据",required = false) Sku sku,@PathVariable Integer id){
        // 设置主键值
        sku.setId(id);
        // 调用skuService实现修改Sku
        skuService.update(sku);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Sku id
     * @return 删除成功
     */
    @ApiOperation(value = "Sku根据ID删除",notes = "根据ID删除Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        // 调用SkuService实现根据主键删除
        skuService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param sku 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Sku条件查询",notes = "条件查询Sku方法详情",tags = {"SkuController"})
    @PostMapping(value = "/search" )
    public Result<List<Sku>> findList(@RequestBody(required = false) @ApiParam(name = "Sku对象",value = "传入JSON数据",required = false) Sku sku){
        // 调用SkuService实现条件查询Sku
        List<Sku> list = skuService.findList(sku);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * Sku分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Sku分页查询",notes = "分页查询Sku方法详情",tags = {"SkuController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<Sku>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用SkuService实现分页查询Sku
        PageInfo<Sku> pageInfo = skuService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Sku分页条件搜索
     * @param sku 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Sku条件分页查询",notes = "分页条件查询Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<Sku>> findPage(@RequestBody(required = false) @ApiParam(name = "Sku对象",value = "传入JSON数据",required = false) Sku sku, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用SkuService实现分页条件查询Sku
        PageInfo<Sku> pageInfo = skuService.findPage(sku, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
