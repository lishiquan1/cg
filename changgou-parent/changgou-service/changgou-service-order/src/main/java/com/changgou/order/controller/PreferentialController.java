package com.changgou.order.controller;

import com.changgou.order.pojo.Preferential;
import com.changgou.order.service.PreferentialService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Preferential表现层
 *
 * @author lishiquan
 */
@Api("PreferentialController")
@RestController
@RequestMapping("/preferential")
@CrossOrigin
public class PreferentialController {

    /**
     * preferential业务层接口
     */
    @Autowired
    private PreferentialService preferentialService;

    /**
     * 查询Preferential全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Preferential", notes = "查询所Preferential有方法详情", tags = {"PreferentialController"})
    @GetMapping
    public Result<List<Preferential>> findAll(){
        // 调用PreferentialService实现查询所有Preferential
        List<Preferential> preferentials = preferentialService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", preferentials) ;
    }

    /**
     * 根据ID查询Preferential数据
     * @param id Preferential id
     * @return 查询成功
     */
    @ApiOperation(value = "Preferential根据ID查询", notes = "根据ID查询Preferential方法详情", tags = {"PreferentialController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Preferential> findById(@PathVariable Integer id){
        // 调用PreferentialService实现根据主键查询Preferential
        Preferential preferential = preferentialService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", preferential);
    }

    /**
     * 新增preferential数据
     * @param preferential Preferential实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Preferential添加", notes = "添加Preferential方法详情", tags = {"PreferentialController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Preferential对象",value = "传入JSON数据",required = true) Preferential preferential){
        // 调用PreferentialService实现添加Preferential
        preferentialService.add(preferential);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Preferential数据
     * @param preferential Preferential实体类
     * @param id Preferential id
     * @return 修改成功
     */
    @ApiOperation(value = "Preferential根据ID修改", notes = "根据ID修改Preferential方法详情", tags = {"PreferentialController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "Preferential对象",value = "传入JSON数据",required = false) Preferential preferential,@PathVariable Integer id){
        // 设置主键值
        preferential.setId(id);
        // 调用preferentialService实现修改Preferential
        preferentialService.update(preferential);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Preferential id
     * @return 删除成功
     */
    @ApiOperation(value = "Preferential根据ID删除", notes = "根据ID删除Preferential方法详情", tags = {"PreferentialController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用PreferentialService实现根据主键删除
        preferentialService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param preferential 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Preferential条件查询", notes = "条件查询Preferential方法详情", tags = {"PreferentialController"})
    @PostMapping("/search")
    public Result<List<Preferential>> findList(@RequestBody(required = false) @ApiParam(name = "Preferential对象",value = "传入JSON数据",required = false) Preferential preferential){
        // 调用PreferentialService实现条件查询Preferential
        List<Preferential> preferentials = preferentialService.findList(preferential);
        return new Result<>(true, StatusCode.OK, "查询成功", preferentials);
    }

    /**
     * Preferential分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Preferential分页查询", notes = "分页查询Preferential方法详情", tags = {"PreferentialController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Preferential>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用PreferentialService实现分页查询Preferential
        PageInfo<Preferential> pageInfo = preferentialService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Preferential分页条件搜索
     * @param preferential 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Preferential条件分页查询",notes = "分页条件查询Preferential方法详情",tags = {"PreferentialController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Preferential>> findPage(@RequestBody(required = false) @ApiParam(name = "Preferential对象",value = "传入JSON数据",required = false) Preferential preferential, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用PreferentialService实现分页条件查询Preferential
        PageInfo<Preferential> pageInfo = preferentialService.findPage(preferential, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
