package com.changgou.seckill.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.SeckillStatus;
import com.changgou.common.entity.StatusCode;
import com.changgou.common.util.TokenDecode;
import com.changgou.seckill.pojo.SeckillOrder;
import com.changgou.seckill.service.SeckillOrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo SeckillOrder表现层
 *
 * @author lishiquan
 */
@Api("SeckillOrderController")
@RestController
@RequestMapping("/seckill/order")
@CrossOrigin
public class SeckillOrderController {

    /**
     * seckillOrder业务层接口
     */
    @Autowired
    private SeckillOrderService seckillOrderService;

    /**
     * 订单状态查询
     * @param id 用户id
     */
    @GetMapping("/query")
    public Result<SeckillStatus> queryStatus(){
        Integer userId = (Integer)TokenDecode.getUserInfo().get("id");
        SeckillStatus seckillStatus = seckillOrderService.queryStatus(userId);
        if (seckillStatus != null){
            return new Result<>(true, StatusCode.OK, "查询状态成功状态", seckillStatus);
        }
        return new Result<>(false, StatusCode.NOTFOUNDERROR, "没有抢购信息", seckillStatus);
    }

    /**
     * 添加秒杀订单
     * @param time 秒杀频道时间
     * @param id 商品id
     * @return 正在排队
     */
    @PostMapping("/add")
    public Result add(String time, Integer id) {
        Integer userId = (Integer) TokenDecode.getUserInfo().get("id");
        seckillOrderService.add(time, id, userId);
        return new Result<>(true, StatusCode.OK, "正在排队");
    }

    /**
     * 查询SeckillOrder全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有SeckillOrder", notes = "查询所SeckillOrder有方法详情", tags = {"SeckillOrderController"})
    @GetMapping
    public Result<List<SeckillOrder>> findAll() {
        // 调用SeckillOrderService实现查询所有SeckillOrder
        List<SeckillOrder> seckillOrders = seckillOrderService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", seckillOrders);
    }

    /**
     * 修改SeckillOrder数据
     * @param seckillOrder SeckillOrder实体类
     * @param id SeckillOrder id
     * @return 修改成功
     */
    @ApiOperation(value = "SeckillOrder根据ID修改", notes = "根据ID修改SeckillOrder方法详情", tags = {"SeckillOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "SeckillOrder对象", value = "传入JSON数据", required = false) SeckillOrder seckillOrder, @PathVariable Integer id) {
        // 设置主键值
        seckillOrder.setId(id);
        // 调用seckillOrderService实现修改SeckillOrder
        seckillOrderService.update(seckillOrder);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param seckillOrder 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "SeckillOrder条件查询", notes = "条件查询SeckillOrder方法详情", tags = {"SeckillOrderController"})
    @PostMapping("/search")
    public Result<List<SeckillOrder>> findList(@RequestBody(required = false) @ApiParam(name = "SeckillOrder对象", value = "传入JSON数据", required = false) SeckillOrder seckillOrder) {
        // 调用SeckillOrderService实现条件查询SeckillOrder
        List<SeckillOrder> seckillOrders = seckillOrderService.findList(seckillOrder);
        return new Result<>(true, StatusCode.OK, "查询成功", seckillOrders);
    }

    /**
     * SeckillOrder分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "SeckillOrder分页查询", notes = "分页查询SeckillOrder方法详情", tags = {"SeckillOrderController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<SeckillOrder>> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        //调用SeckillOrderService实现分页查询SeckillOrder
        PageInfo<SeckillOrder> pageInfo = seckillOrderService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * SeckillOrder分页条件搜索
     * @param seckillOrder 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "SeckillOrder条件分页查询", notes = "分页条件查询SeckillOrder方法详情", tags = {"SeckillOrderController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<SeckillOrder>> findPage(@RequestBody(required = false) @ApiParam(name = "SeckillOrder对象", value = "传入JSON数据", required = false) SeckillOrder seckillOrder, @PathVariable Integer page, @PathVariable Integer size) {
        //调用SeckillOrderService实现分页条件查询SeckillOrder
        PageInfo<SeckillOrder> pageInfo = seckillOrderService.findPage(seckillOrder, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
