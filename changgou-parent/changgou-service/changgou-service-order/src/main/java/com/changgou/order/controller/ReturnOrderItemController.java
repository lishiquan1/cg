package com.changgou.order.controller;

import com.changgou.order.pojo.ReturnOrderItem;
import com.changgou.order.service.ReturnOrderItemService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo ReturnOrderItem表现层
 *
 * @author lishiquan
 */
@Api("ReturnOrderItemController")
@RestController
@RequestMapping("/returnOrderItem")
@CrossOrigin
public class ReturnOrderItemController {

    /**
     * returnOrderItem业务层接口
     */
    @Autowired
    private ReturnOrderItemService returnOrderItemService;

    /**
     * 查询ReturnOrderItem全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有ReturnOrderItem", notes = "查询所ReturnOrderItem有方法详情", tags = {"ReturnOrderItemController"})
    @GetMapping
    public Result<List<ReturnOrderItem>> findAll(){
        // 调用ReturnOrderItemService实现查询所有ReturnOrderItem
        List<ReturnOrderItem> returnOrderItems = returnOrderItemService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", returnOrderItems) ;
    }

    /**
     * 根据ID查询ReturnOrderItem数据
     * @param id ReturnOrderItem id
     * @return 查询成功
     */
    @ApiOperation(value = "ReturnOrderItem根据ID查询", notes = "根据ID查询ReturnOrderItem方法详情", tags = {"ReturnOrderItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<ReturnOrderItem> findById(@PathVariable Integer id){
        // 调用ReturnOrderItemService实现根据主键查询ReturnOrderItem
        ReturnOrderItem returnOrderItem = returnOrderItemService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", returnOrderItem);
    }

    /**
     * 新增returnOrderItem数据
     * @param returnOrderItem ReturnOrderItem实体类
     * @return 添加成功
     */
    @ApiOperation(value = "ReturnOrderItem添加", notes = "添加ReturnOrderItem方法详情", tags = {"ReturnOrderItemController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "ReturnOrderItem对象",value = "传入JSON数据",required = true) ReturnOrderItem returnOrderItem){
        // 调用ReturnOrderItemService实现添加ReturnOrderItem
        returnOrderItemService.add(returnOrderItem);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改ReturnOrderItem数据
     * @param returnOrderItem ReturnOrderItem实体类
     * @param id ReturnOrderItem id
     * @return 修改成功
     */
    @ApiOperation(value = "ReturnOrderItem根据ID修改", notes = "根据ID修改ReturnOrderItem方法详情", tags = {"ReturnOrderItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "ReturnOrderItem对象",value = "传入JSON数据",required = false) ReturnOrderItem returnOrderItem,@PathVariable Integer id){
        // 设置主键值
        returnOrderItem.setId(id);
        // 调用returnOrderItemService实现修改ReturnOrderItem
        returnOrderItemService.update(returnOrderItem);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id ReturnOrderItem id
     * @return 删除成功
     */
    @ApiOperation(value = "ReturnOrderItem根据ID删除", notes = "根据ID删除ReturnOrderItem方法详情", tags = {"ReturnOrderItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        // 调用ReturnOrderItemService实现根据主键删除
        returnOrderItemService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param returnOrderItem 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "ReturnOrderItem条件查询", notes = "条件查询ReturnOrderItem方法详情", tags = {"ReturnOrderItemController"})
    @PostMapping("/search")
    public Result<List<ReturnOrderItem>> findList(@RequestBody(required = false) @ApiParam(name = "ReturnOrderItem对象",value = "传入JSON数据",required = false) ReturnOrderItem returnOrderItem){
        // 调用ReturnOrderItemService实现条件查询ReturnOrderItem
        List<ReturnOrderItem> returnOrderItems = returnOrderItemService.findList(returnOrderItem);
        return new Result<>(true, StatusCode.OK, "查询成功", returnOrderItems);
    }

    /**
     * ReturnOrderItem分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "ReturnOrderItem分页查询", notes = "分页查询ReturnOrderItem方法详情", tags = {"ReturnOrderItemController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<ReturnOrderItem>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用ReturnOrderItemService实现分页查询ReturnOrderItem
        PageInfo<ReturnOrderItem> pageInfo = returnOrderItemService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * ReturnOrderItem分页条件搜索
     * @param returnOrderItem 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "ReturnOrderItem条件分页查询",notes = "分页条件查询ReturnOrderItem方法详情",tags = {"ReturnOrderItemController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<ReturnOrderItem>> findPage(@RequestBody(required = false) @ApiParam(name = "ReturnOrderItem对象",value = "传入JSON数据",required = false) ReturnOrderItem returnOrderItem, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用ReturnOrderItemService实现分页条件查询ReturnOrderItem
        PageInfo<ReturnOrderItem> pageInfo = returnOrderItemService.findPage(returnOrderItem, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
