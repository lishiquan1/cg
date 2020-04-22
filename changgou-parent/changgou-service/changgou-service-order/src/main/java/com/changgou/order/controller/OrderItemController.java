package com.changgou.order.controller;

import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.OrderItemService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo OrderItem表现层
 *
 * @author lishiquan
 */
@Api("OrderItemController")
@RestController
@RequestMapping("/orderItem")
@CrossOrigin
public class OrderItemController {

    /**
     * orderItem业务层接口
     */
    @Autowired
    private OrderItemService orderItemService;

    /**
     * 查询OrderItem全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有OrderItem", notes = "查询所OrderItem有方法详情", tags = {"OrderItemController"})
    @GetMapping
    public Result<List<OrderItem>> findAll(){
        // 调用OrderItemService实现查询所有OrderItem
        List<OrderItem> orderItems = orderItemService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", orderItems) ;
    }

    /**
     * 根据ID查询OrderItem数据
     * @param id OrderItem id
     * @return 查询成功
     */
    @ApiOperation(value = "OrderItem根据ID查询", notes = "根据ID查询OrderItem方法详情", tags = {"OrderItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<OrderItem> findById(@PathVariable Integer id){
        // 调用OrderItemService实现根据主键查询OrderItem
        OrderItem orderItem = orderItemService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", orderItem);
    }

    /**
     * 新增orderItem数据
     * @param orderItem OrderItem实体类
     * @return 添加成功
     */
    @ApiOperation(value = "OrderItem添加", notes = "添加OrderItem方法详情", tags = {"OrderItemController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "OrderItem对象",value = "传入JSON数据",required = true) OrderItem orderItem){
        // 调用OrderItemService实现添加OrderItem
        orderItemService.add(orderItem);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改OrderItem数据
     * @param orderItem OrderItem实体类
     * @param id OrderItem id
     * @return 修改成功
     */
    @ApiOperation(value = "OrderItem根据ID修改", notes = "根据ID修改OrderItem方法详情", tags = {"OrderItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "OrderItem对象",value = "传入JSON数据",required = false) OrderItem orderItem,@PathVariable Integer id){
        // 设置主键值
        orderItem.setId(id);
        // 调用orderItemService实现修改OrderItem
        orderItemService.update(orderItem);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id OrderItem id
     * @return 删除成功
     */
    @ApiOperation(value = "OrderItem根据ID删除", notes = "根据ID删除OrderItem方法详情", tags = {"OrderItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用OrderItemService实现根据主键删除
        orderItemService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param orderItem 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "OrderItem条件查询", notes = "条件查询OrderItem方法详情", tags = {"OrderItemController"})
    @PostMapping("/search")
    public Result<List<OrderItem>> findList(@RequestBody(required = false) @ApiParam(name = "OrderItem对象",value = "传入JSON数据",required = false) OrderItem orderItem){
        // 调用OrderItemService实现条件查询OrderItem
        List<OrderItem> orderItems = orderItemService.findList(orderItem);
        return new Result<>(true, StatusCode.OK, "查询成功", orderItems);
    }

    /**
     * OrderItem分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "OrderItem分页查询", notes = "分页查询OrderItem方法详情", tags = {"OrderItemController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderItem>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用OrderItemService实现分页查询OrderItem
        PageInfo<OrderItem> pageInfo = orderItemService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * OrderItem分页条件搜索
     * @param orderItem 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "OrderItem条件分页查询",notes = "分页条件查询OrderItem方法详情",tags = {"OrderItemController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<OrderItem>> findPage(@RequestBody(required = false) @ApiParam(name = "OrderItem对象",value = "传入JSON数据",required = false) OrderItem orderItem, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用OrderItemService实现分页条件查询OrderItem
        PageInfo<OrderItem> pageInfo = orderItemService.findPage(orderItem, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
