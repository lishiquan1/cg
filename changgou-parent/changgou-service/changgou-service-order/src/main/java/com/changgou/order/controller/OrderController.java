package com.changgou.order.controller;

import com.changgou.order.pojo.Order;
import com.changgou.order.service.OrderService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Order表现层
 *
 * @author lishiquan
 */
@Api("OrderController")
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    /**
     * order业务层接口
     */
    @Autowired
    private OrderService orderService;

    /**
     * 查询Order全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Order", notes = "查询所Order有方法详情", tags = {"OrderController"})
    @GetMapping
    public Result<List<Order>> findAll(){
        // 调用OrderService实现查询所有Order
        List<Order> orders = orderService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", orders) ;
    }

    /**
     * 根据ID查询Order数据
     * @param id Order id
     * @return 查询成功
     */
    @ApiOperation(value = "Order根据ID查询", notes = "根据ID查询Order方法详情", tags = {"OrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Order> findById(@PathVariable Integer id){
        // 调用OrderService实现根据主键查询Order
        Order order = orderService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", order);
    }

    /**
     * 新增order数据
     * @param order Order实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Order添加", notes = "添加Order方法详情", tags = {"OrderController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Order对象",value = "传入JSON数据",required = true) Order order){
        // 调用OrderService实现添加Order
        orderService.add(order);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Order数据
     * @param order Order实体类
     * @param id Order id
     * @return 修改成功
     */
    @ApiOperation(value = "Order根据ID修改", notes = "根据ID修改Order方法详情", tags = {"OrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "Order对象",value = "传入JSON数据",required = false) Order order,@PathVariable Integer id){
        // 设置主键值
        order.setId(id);
        // 调用orderService实现修改Order
        orderService.update(order);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Order id
     * @return 删除成功
     */
    @ApiOperation(value = "Order根据ID删除", notes = "根据ID删除Order方法详情", tags = {"OrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用OrderService实现根据主键删除
        orderService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param order 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Order条件查询", notes = "条件查询Order方法详情", tags = {"OrderController"})
    @PostMapping("/search")
    public Result<List<Order>> findList(@RequestBody(required = false) @ApiParam(name = "Order对象",value = "传入JSON数据",required = false) Order order){
        // 调用OrderService实现条件查询Order
        List<Order> orders = orderService.findList(order);
        return new Result<>(true, StatusCode.OK, "查询成功", orders);
    }

    /**
     * Order分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Order分页查询", notes = "分页查询Order方法详情", tags = {"OrderController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Order>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用OrderService实现分页查询Order
        PageInfo<Order> pageInfo = orderService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Order分页条件搜索
     * @param order 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Order条件分页查询",notes = "分页条件查询Order方法详情",tags = {"OrderController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Order>> findPage(@RequestBody(required = false) @ApiParam(name = "Order对象",value = "传入JSON数据",required = false) Order order, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用OrderService实现分页条件查询Order
        PageInfo<Order> pageInfo = orderService.findPage(order, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
