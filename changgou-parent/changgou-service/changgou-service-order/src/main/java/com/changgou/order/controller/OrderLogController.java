package com.changgou.order.controller;

import com.changgou.order.pojo.OrderLog;
import com.changgou.order.service.OrderLogService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo OrderLog表现层
 *
 * @author lishiquan
 */
@Api("OrderLogController")
@RestController
@RequestMapping("/orderLog")
@CrossOrigin
public class OrderLogController {

    /**
     * orderLog业务层接口
     */
    @Autowired
    private OrderLogService orderLogService;

    /**
     * 查询OrderLog全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有OrderLog", notes = "查询所OrderLog有方法详情", tags = {"OrderLogController"})
    @GetMapping
    public Result<List<OrderLog>> findAll(){
        // 调用OrderLogService实现查询所有OrderLog
        List<OrderLog> orderLogs = orderLogService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", orderLogs) ;
    }

    /**
     * 根据ID查询OrderLog数据
     * @param id OrderLog id
     * @return 查询成功
     */
    @ApiOperation(value = "OrderLog根据ID查询", notes = "根据ID查询OrderLog方法详情", tags = {"OrderLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<OrderLog> findById(@PathVariable Integer id){
        // 调用OrderLogService实现根据主键查询OrderLog
        OrderLog orderLog = orderLogService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", orderLog);
    }

    /**
     * 新增orderLog数据
     * @param orderLog OrderLog实体类
     * @return 添加成功
     */
    @ApiOperation(value = "OrderLog添加", notes = "添加OrderLog方法详情", tags = {"OrderLogController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "OrderLog对象",value = "传入JSON数据",required = true) OrderLog orderLog){
        // 调用OrderLogService实现添加OrderLog
        orderLogService.add(orderLog);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改OrderLog数据
     * @param orderLog OrderLog实体类
     * @param id OrderLog id
     * @return 修改成功
     */
    @ApiOperation(value = "OrderLog根据ID修改", notes = "根据ID修改OrderLog方法详情", tags = {"OrderLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "OrderLog对象",value = "传入JSON数据",required = false) OrderLog orderLog,@PathVariable Integer id){
        // 设置主键值
        orderLog.setId(id);
        // 调用orderLogService实现修改OrderLog
        orderLogService.update(orderLog);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id OrderLog id
     * @return 删除成功
     */
    @ApiOperation(value = "OrderLog根据ID删除", notes = "根据ID删除OrderLog方法详情", tags = {"OrderLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用OrderLogService实现根据主键删除
        orderLogService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param orderLog 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "OrderLog条件查询", notes = "条件查询OrderLog方法详情", tags = {"OrderLogController"})
    @PostMapping("/search")
    public Result<List<OrderLog>> findList(@RequestBody(required = false) @ApiParam(name = "OrderLog对象",value = "传入JSON数据",required = false) OrderLog orderLog){
        // 调用OrderLogService实现条件查询OrderLog
        List<OrderLog> orderLogs = orderLogService.findList(orderLog);
        return new Result<>(true, StatusCode.OK, "查询成功", orderLogs);
    }

    /**
     * OrderLog分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "OrderLog分页查询", notes = "分页查询OrderLog方法详情", tags = {"OrderLogController"})
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderLog>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用OrderLogService实现分页查询OrderLog
        PageInfo<OrderLog> pageInfo = orderLogService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * OrderLog分页条件搜索
     * @param orderLog 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "OrderLog条件分页查询",notes = "分页条件查询OrderLog方法详情",tags = {"OrderLogController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderLog>> findPage(@RequestBody(required = false) @ApiParam(name = "OrderLog对象",value = "传入JSON数据",required = false) OrderLog orderLog, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用OrderLogService实现分页条件查询OrderLog
        PageInfo<OrderLog> pageInfo = orderLogService.findPage(orderLog, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
