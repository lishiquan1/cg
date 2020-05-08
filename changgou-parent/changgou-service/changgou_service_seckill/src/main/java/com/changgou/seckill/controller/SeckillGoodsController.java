package com.changgou.seckill.controller;

import com.changgou.common.util.DateUtil;
import com.changgou.seckill.pojo.SeckillGoods;
import com.changgou.seckill.service.SeckillGoodsService;
import com.github.pagehelper.PageInfo;
import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Demo SeckillGoods表现层
 *
 * @author lishiquan
 */
@Api("SeckillGoodsController")
@RestController
@RequestMapping("/seckill/goods")
@CrossOrigin
public class SeckillGoodsController {

    /**
     * seckillGoods业务层接口
     */
    @Autowired
    private SeckillGoodsService seckillGoodsService;

    /**
     * 查询秒杀商品详情
     * @param time 秒杀频道时间
     * @param id 商品id
     * @return 查询成功
     */
    @ApiOperation(value = "查询秒杀商品详情", notes = "查询秒杀商品详情", tags = {"SeckillGoodsController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "param", name = "time", value = "秒杀频道时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "param", name = "id", value = "商品id", required = true, dataType = "Integer")
    })
    @GetMapping("/item")
    public Result<SeckillGoods> item(String time, Integer id){
        SeckillGoods seckillGoods= seckillGoodsService.item(time, id);
        return new Result<>(true, StatusCode.OK, "查询成功", seckillGoods);
    }

    /**
     * 获取频道时间
     * @return 查询频道时间成功
     */
    @ApiOperation(value = "获取频道时间", notes = "获取频道时间", tags = {"SeckillGoodsController"})
    @GetMapping("/menus")
    public Result<List<Date>> menus() {
        List<Date> menus = DateUtil.getDateMenus();
        return new Result<>(true, StatusCode.OK, "查询频道时间成功", menus);
    }

    /**
     * 根据时间区间查询秒杀商品频道列表数据
     * @param time 秒杀频道时间
     * @return 查询成功
     */
    @ApiOperation(value = "根据时间区间查询秒杀商品频道列表数据", notes = "根据时间区间查询秒杀商品频道列表数据", tags = {"SeckillGoodsController"})
    @ApiImplicitParam(paramType = "param", name = "time", value = "秒杀频道时间", required = true, dataType = "String")
    @GetMapping("/list")
    public Result<List<SeckillGoods>> list(String time) {
        List<SeckillGoods> seckillGoodsList = seckillGoodsService.list(time);
        return new Result<>(true, StatusCode.OK, "查询成功", seckillGoodsList);
    }

    /**
     * 查询SeckillGoods全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有SeckillGoods", notes = "查询所SeckillGoods有方法详情", tags = {"SeckillGoodsController"})
    @GetMapping
    public Result<List<SeckillGoods>> findAll() {
        // 调用SeckillGoodsService实现查询所有SeckillGoods
        List<SeckillGoods> seckillGoodss = seckillGoodsService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", seckillGoodss);
    }

    /**
     * 根据ID查询SeckillGoods数据
     * @param id SeckillGoods id
     * @return 查询成功
     */
    @ApiOperation(value = "SeckillGoods根据ID查询", notes = "根据ID查询SeckillGoods方法详情", tags = {"SeckillGoodsController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<SeckillGoods> findById(@PathVariable Integer id) {
        // 调用SeckillGoodsService实现根据主键查询SeckillGoods
        SeckillGoods seckillGoods = seckillGoodsService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", seckillGoods);
    }

    /**
     * 新增seckillGoods数据
     * @param seckillGoods SeckillGoods实体类
     * @return 添加成功
     */
    @ApiOperation(value = "SeckillGoods添加", notes = "添加SeckillGoods方法详情", tags = {"SeckillGoodsController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "SeckillGoods对象", value = "传入JSON数据", required = true) SeckillGoods seckillGoods) {
        // 调用SeckillGoodsService实现添加SeckillGoods
        seckillGoodsService.add(seckillGoods);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改SeckillGoods数据
     * @param seckillGoods SeckillGoods实体类
     * @param id SeckillGoods id
     * @return 修改成功
     */
    @ApiOperation(value = "SeckillGoods根据ID修改", notes = "根据ID修改SeckillGoods方法详情", tags = {"SeckillGoodsController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/{id}")
    public Result update(@RequestBody @ApiParam(name = "SeckillGoods对象", value = "传入JSON数据", required = false) SeckillGoods seckillGoods, @PathVariable Integer id) {
        // 设置主键值
        seckillGoods.setId(id);
        // 调用seckillGoodsService实现修改SeckillGoods
        seckillGoodsService.update(seckillGoods);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id SeckillGoods id
     * @return 删除成功
     */
    @ApiOperation(value = "SeckillGoods根据ID删除", notes = "根据ID删除SeckillGoods方法详情", tags = {"SeckillGoodsController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        // 调用SeckillGoodsService实现根据主键删除
        seckillGoodsService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param seckillGoods 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "SeckillGoods条件查询", notes = "条件查询SeckillGoods方法详情", tags = {"SeckillGoodsController"})
    @PostMapping("/search")
    public Result<List<SeckillGoods>> findList(@RequestBody(required = false) @ApiParam(name = "SeckillGoods对象", value = "传入JSON数据", required = false) SeckillGoods seckillGoods) {
        // 调用SeckillGoodsService实现条件查询SeckillGoods
        List<SeckillGoods> seckillGoodss = seckillGoodsService.findList(seckillGoods);
        return new Result<>(true, StatusCode.OK, "查询成功", seckillGoodss);
    }

    /**
     * SeckillGoods分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "SeckillGoods分页查询", notes = "分页查询SeckillGoods方法详情", tags = {"SeckillGoodsController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<SeckillGoods>> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        //调用SeckillGoodsService实现分页查询SeckillGoods
        PageInfo<SeckillGoods> pageInfo = seckillGoodsService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * SeckillGoods分页条件搜索
     * @param seckillGoods 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "SeckillGoods条件分页查询", notes = "分页条件查询SeckillGoods方法详情", tags = {"SeckillGoodsController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<SeckillGoods>> findPage(@RequestBody(required = false) @ApiParam(name = "SeckillGoods对象", value = "传入JSON数据", required = false) SeckillGoods seckillGoods, @PathVariable Integer page, @PathVariable Integer size) {
        //调用SeckillGoodsService实现分页条件查询SeckillGoods
        PageInfo<SeckillGoods> pageInfo = seckillGoodsService.findPage(seckillGoods, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
