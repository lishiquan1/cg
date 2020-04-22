package com.changgou.order.controller;

import com.changgou.order.pojo.ReturnOrder;
import com.changgou.order.service.ReturnOrderService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo ReturnOrder表现层
 *
 * @author lishiquan
 */
@Api("ReturnOrderController")
@RestController
@RequestMapping("/returnOrder")
@CrossOrigin
public class ReturnOrderController {

    /**
     * returnOrder业务层接口
     */
    @Autowired
    private ReturnOrderService returnOrderService;

    /**
     * 查询ReturnOrder全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有ReturnOrder", notes = "查询所ReturnOrder有方法详情", tags = {"ReturnOrderController"})
    @GetMapping
    public Result<List<ReturnOrder>> findAll(){
        // 调用ReturnOrderService实现查询所有ReturnOrder
        List<ReturnOrder> returnOrders = returnOrderService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", returnOrders) ;
    }

    /**
     * 根据ID查询ReturnOrder数据
     * @param id ReturnOrder id
     * @return 查询成功
     */
    @ApiOperation(value = "ReturnOrder根据ID查询", notes = "根据ID查询ReturnOrder方法详情", tags = {"ReturnOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<ReturnOrder> findById(@PathVariable Integer id){
        // 调用ReturnOrderService实现根据主键查询ReturnOrder
        ReturnOrder returnOrder = returnOrderService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", returnOrder);
    }

    /**
     * 新增returnOrder数据
     * @param returnOrder ReturnOrder实体类
     * @return 添加成功
     */
    @ApiOperation(value = "ReturnOrder添加", notes = "添加ReturnOrder方法详情", tags = {"ReturnOrderController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "ReturnOrder对象",value = "传入JSON数据",required = true) ReturnOrder returnOrder){
        // 调用ReturnOrderService实现添加ReturnOrder
        returnOrderService.add(returnOrder);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改ReturnOrder数据
     * @param returnOrder ReturnOrder实体类
     * @param id ReturnOrder id
     * @return 修改成功
     */
    @ApiOperation(value = "ReturnOrder根据ID修改", notes = "根据ID修改ReturnOrder方法详情", tags = {"ReturnOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "ReturnOrder对象",value = "传入JSON数据",required = false) ReturnOrder returnOrder,@PathVariable Integer id){
        // 设置主键值
        returnOrder.setId(id);
        // 调用returnOrderService实现修改ReturnOrder
        returnOrderService.update(returnOrder);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id ReturnOrder id
     * @return 删除成功
     */
    @ApiOperation(value = "ReturnOrder根据ID删除", notes = "根据ID删除ReturnOrder方法详情", tags = {"ReturnOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用ReturnOrderService实现根据主键删除
        returnOrderService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param returnOrder 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "ReturnOrder条件查询", notes = "条件查询ReturnOrder方法详情", tags = {"ReturnOrderController"})
    @PostMapping("/search")
    public Result<List<ReturnOrder>> findList(@RequestBody(required = false) @ApiParam(name = "ReturnOrder对象",value = "传入JSON数据",required = false) ReturnOrder returnOrder){
        // 调用ReturnOrderService实现条件查询ReturnOrder
        List<ReturnOrder> returnOrders = returnOrderService.findList(returnOrder);
        return new Result<>(true, StatusCode.OK, "查询成功", returnOrders);
    }

    /**
     * ReturnOrder分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "ReturnOrder分页查询", notes = "分页查询ReturnOrder方法详情", tags = {"ReturnOrderController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<ReturnOrder>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用ReturnOrderService实现分页查询ReturnOrder
        PageInfo<ReturnOrder> pageInfo = returnOrderService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * ReturnOrder分页条件搜索
     * @param returnOrder 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "ReturnOrder条件分页查询",notes = "分页条件查询ReturnOrder方法详情",tags = {"ReturnOrderController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<ReturnOrder>> findPage(@RequestBody(required = false) @ApiParam(name = "ReturnOrder对象",value = "传入JSON数据",required = false) ReturnOrder returnOrder, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用ReturnOrderService实现分页条件查询ReturnOrder
        PageInfo<ReturnOrder> pageInfo = returnOrderService.findPage(returnOrder, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
