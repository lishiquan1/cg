package com.changgou.order.controller;

import com.changgou.order.pojo.OrderConfig;
import com.changgou.order.service.OrderConfigService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo OrderConfig表现层
 *
 * @author lishiquan
 */
@Api("OrderConfigController")
@RestController
@RequestMapping("/orderConfig")
@CrossOrigin
public class OrderConfigController {

    /**
     * orderConfig业务层接口
     */
    @Autowired
    private OrderConfigService orderConfigService;

    /**
     * 查询OrderConfig全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有OrderConfig", notes = "查询所OrderConfig有方法详情", tags = {"OrderConfigController"})
    @GetMapping
    public Result<List<OrderConfig>> findAll(){
        // 调用OrderConfigService实现查询所有OrderConfig
        List<OrderConfig> orderConfigs = orderConfigService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", orderConfigs) ;
    }

    /**
     * 根据ID查询OrderConfig数据
     * @param id OrderConfig id
     * @return 查询成功
     */
    @ApiOperation(value = "OrderConfig根据ID查询", notes = "根据ID查询OrderConfig方法详情", tags = {"OrderConfigController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<OrderConfig> findById(@PathVariable Integer id){
        // 调用OrderConfigService实现根据主键查询OrderConfig
        OrderConfig orderConfig = orderConfigService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", orderConfig);
    }

    /**
     * 新增orderConfig数据
     * @param orderConfig OrderConfig实体类
     * @return 添加成功
     */
    @ApiOperation(value = "OrderConfig添加", notes = "添加OrderConfig方法详情", tags = {"OrderConfigController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "OrderConfig对象",value = "传入JSON数据",required = true) OrderConfig orderConfig){
        // 调用OrderConfigService实现添加OrderConfig
        orderConfigService.add(orderConfig);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改OrderConfig数据
     * @param orderConfig OrderConfig实体类
     * @param id OrderConfig id
     * @return 修改成功
     */
    @ApiOperation(value = "OrderConfig根据ID修改", notes = "根据ID修改OrderConfig方法详情", tags = {"OrderConfigController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "OrderConfig对象",value = "传入JSON数据",required = false) OrderConfig orderConfig,@PathVariable Integer id){
        // 设置主键值
        orderConfig.setId(id);
        // 调用orderConfigService实现修改OrderConfig
        orderConfigService.update(orderConfig);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id OrderConfig id
     * @return 删除成功
     */
    @ApiOperation(value = "OrderConfig根据ID删除", notes = "根据ID删除OrderConfig方法详情", tags = {"OrderConfigController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用OrderConfigService实现根据主键删除
        orderConfigService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param orderConfig 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "OrderConfig条件查询", notes = "条件查询OrderConfig方法详情", tags = {"OrderConfigController"})
    @PostMapping("/search")
    public Result<List<OrderConfig>> findList(@RequestBody(required = false) @ApiParam(name = "OrderConfig对象",value = "传入JSON数据",required = false) OrderConfig orderConfig){
        // 调用OrderConfigService实现条件查询OrderConfig
        List<OrderConfig> orderConfigs = orderConfigService.findList(orderConfig);
        return new Result<>(true, StatusCode.OK, "查询成功", orderConfigs);
    }

    /**
     * OrderConfig分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "OrderConfig分页查询", notes = "分页查询OrderConfig方法详情", tags = {"OrderConfigController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderConfig>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用OrderConfigService实现分页查询OrderConfig
        PageInfo<OrderConfig> pageInfo = orderConfigService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * OrderConfig分页条件搜索
     * @param orderConfig 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "OrderConfig条件分页查询",notes = "分页条件查询OrderConfig方法详情",tags = {"OrderConfigController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderConfig>> findPage(@RequestBody(required = false) @ApiParam(name = "OrderConfig对象",value = "传入JSON数据",required = false) OrderConfig orderConfig, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用OrderConfigService实现分页条件查询OrderConfig
        PageInfo<OrderConfig> pageInfo = orderConfigService.findPage(orderConfig, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
