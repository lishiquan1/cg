package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Spu表现层
 *
 * @author lishiquan
 */
@Api(value = "SpuController")
@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {
    /**
     * spu业务层接口
     */
    @Autowired
    private SpuService spuService;

    /**
     * 商品上架
     * @param ids Spu id
     */
    @ApiOperation(value = "商品批量上架", notes = "根据Spu id批量改变商品状态为上架", tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer[]")
    @PutMapping("/put/many")
    public Result put(@RequestBody @ApiParam(name = "Spu id数组", value = "传入JSON数据", required = true) Integer[] ids) {
        spuService.putMany(ids);
        return new Result<>(true, StatusCode.OK, "商品批量上架成功");
    }

    /**
     * 商品上架
     * @param id Spu id
     */
    @ApiOperation(value = "商品上架", notes = "根据Spu id改变商品状态为上架", tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/put/{id}")
    public Result put(@PathVariable Integer id) {
        spuService.put(id);
        return new Result<>(true, StatusCode.OK, "商品上架成功");
    }

    /**
     * 商品下架
     * @param id Spu id
     */
    @ApiOperation(value = "商品下架", notes = "根据Spu id改变商品状态为下架", tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/pull/{id}")
    public Result pull(@PathVariable Integer id) {
        spuService.pull(id);
        return new Result<>(true, StatusCode.OK, "商品下架成功");
    }

    /**
     * 商品审核
     * @param id Spu id
     */
    @ApiOperation(value = "审核商品", notes = "根据Spu id改变商品审核状态为已审核", tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping("/audit/{id}")
    public Result audit(@PathVariable Integer id) {
        spuService.audit(id);
        return new Result<>(true, StatusCode.OK, "商品审核通过");
    }

    /**
     * 根据id查询Goods信息
     * @param id Spu id
     * @return 查询商品成功
     */
    @ApiOperation(value = "Spu根据ID查询商品", notes = "根据ID查询商品详情", tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/goods/{id}")
    public Result<Goods> findGoods(@PathVariable Integer id) {
        Goods goods = spuService.findGoods(id);
        return new Result<>(true, StatusCode.OK, "查询商品成功", goods);
    }

    /**
     * 添加/修改 商品
     * @param goods spu + sku集合
     * @return 添加商品成功
     */
    @ApiOperation(value = "Sku添加或修改商品", notes = "添加或修改商品", tags = {"SpuController"})
    @PostMapping("/save")
    public Result saveGoods(@RequestBody @ApiParam(name = "Goods对象", value = "传入JSON数据", required = true) Goods goods) {
        String message = "添加商品成功";
        // spu id存在则修改商品
        if (goods != null) {
            message = "修改商品成功";
        }
        spuService.saveGoods(goods);
        return new Result<>(true, StatusCode.OK, message);
    }

    /**
     * 查询Spu全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Spu", notes = "查询所Spu有方法详情", tags = {"SpuController"})
    @GetMapping
    public Result<List<Spu>> findAll() {
        // 调用SpuService实现查询所有Spu
        List<Spu> list = spuService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据ID查询Spu数据
     * @param id Spu id
     * @return 查询成功
     */
    @ApiOperation(value = "Spu根据ID查询", notes = "根据ID查询Spu方法详情", tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable Integer id) {
        // 调用SpuService实现根据主键查询Spu
        Spu spu = spuService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", spu);
    }

    /**
     * 新增spu数据
     * @param spu Spu实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Spu添加", notes = "添加Spu方法详情", tags = {"SpuController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Spu对象", value = "传入JSON数据", required = true) Spu spu) {
        // 调用SpuService实现添加Spu
        spuService.add(spu);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Spu数据
     * @param spu Spu实体类
     * @param id Spu id
     * @return 修改成功
     */
    @ApiOperation(value = "Spu根据ID修改", notes = "根据ID修改Spu方法详情", tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody @ApiParam(name = "Spu对象", value = "传入JSON数据", required = false) Spu spu, @PathVariable Integer id) {
        // 设置主键值
        spu.setId(id);
        // 调用spuService实现修改Spu
        spuService.update(spu);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Spu id
     * @return 删除成功
     */
    @ApiOperation(value = "Spu根据ID删除", notes = "根据ID删除Spu方法详情", tags = {"SpuController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        // 调用SpuService实现根据主键删除
        spuService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param spu 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Spu条件查询", notes = "条件查询Spu方法详情", tags = {"SpuController"})
    @PostMapping(value = "/search")
    public Result<List<Spu>> findList(@RequestBody(required = false) @ApiParam(name = "Spu对象", value = "传入JSON数据", required = false) Spu spu) {
        // 调用SpuService实现条件查询Spu
        List<Spu> list = spuService.findList(spu);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * Spu分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Spu分页查询", notes = "分页查询Spu方法详情", tags = {"SpuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Spu>> findPage(@PathVariable Integer page, @PathVariable Integer size) {
        //调用SpuService实现分页查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Spu分页条件搜索
     * @param spu 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Spu条件分页查询", notes = "分页条件查询Spu方法详情", tags = {"SpuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Spu>> findPage(@RequestBody(required = false) @ApiParam(name = "Spu对象", value = "传入JSON数据", required = false) Spu spu, @PathVariable Integer page, @PathVariable Integer size) {
        //调用SpuService实现分页条件查询Spu
        PageInfo<Spu> pageInfo = spuService.findPage(spu, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
