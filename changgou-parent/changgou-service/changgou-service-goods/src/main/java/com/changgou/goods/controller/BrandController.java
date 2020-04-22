package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Brand表现层
 *
 * @author lishiquan
 */
@Api(value = "BrandController")
@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {

    /**
     * brand业务层接口
     */
    @Autowired
    private BrandService brandService;

    /**
     * 根据分类id查询品牌集合
     * @param cid Category分类id
     * @return 查询成功
     */
    @ApiOperation(value = "根据分类id查询品牌列表",notes = "根据分类id查询品牌列表",tags = {"BrandController"})
    @ApiImplicitParam(paramType = "path", name = "cid", value = "分类id", required = true, dataType = "Integer")
    @GetMapping("/category/{cid}")
    public Result<List<Brand>> findByCategory(@PathVariable Integer cid){
        List<Brand> list = brandService.findByCategory(cid);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 查询Brand全部数据
     * @return 查询成功
     */
    @ApiOperation(value = "查询所有Brand",notes = "查询所Brand有方法详情",tags = {"BrandController"})
    @GetMapping
    public Result<List<Brand>> findAll(){
        // 调用BrandService实现查询所有Brand
        List<Brand> list = brandService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list) ;
    }

    /**
     * 根据ID查询Brand数据
     * @param id Brand id
     * @return 查询成功
     */
    @ApiOperation(value = "Brand根据ID查询",notes = "根据ID查询Brand方法详情",tags = {"BrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable Integer id){
        // 调用BrandService实现根据主键查询Brand
        Brand brand = brandService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", brand);
    }

    /**
     * 新增brand数据
     * @param brand Brand实体类
     * @return 添加成功
     */
    @ApiOperation(value = "Brand添加",notes = "添加Brand方法详情",tags = {"BrandController"})
    @PostMapping
    public Result add(@RequestBody @ApiParam(name = "Brand对象",value = "传入JSON数据",required = true) Brand brand){
        // 调用BrandService实现添加Brand
        brandService.add(brand);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改Brand数据
     * @param brand Brand实体类
     * @param id Brand id
     * @return 修改成功
     */
    @ApiOperation(value = "Brand根据ID修改",notes = "根据ID修改Brand方法详情",tags = {"BrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Brand对象",value = "传入JSON数据",required = false) Brand brand, @PathVariable Integer id){
        // 设置主键值
        brand.setId(id);
        // 调用brandService实现修改Brand
        brandService.update(brand);
        return new Result<>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据ID删除数据
     * @param id Brand id
     * @return 删除成功
     */
    @ApiOperation(value = "Brand根据ID删除",notes = "根据ID删除Brand方法详情",tags = {"BrandController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        // 调用BrandService实现根据主键删除
        brandService.delete(id);
        return new Result<>(true, StatusCode.OK, "删除成功");
    }

    /**
     * 多条件搜索品牌数据
     * @param brand 查询条件
     * @return 查询结果
     */
    @ApiOperation(value = "Brand条件查询",notes = "条件查询Brand方法详情",tags = {"BrandController"})
    @PostMapping(value = "/search" )
    public Result<List<Brand>> findList(@RequestBody(required = false) @ApiParam(name = "Brand对象",value = "传入JSON数据",required = false) Brand brand){
        // 调用BrandService实现条件查询Brand
        List<Brand> list = brandService.findByCategory(brand);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * Brand分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Brand分页查询",notes = "分页查询Brand方法详情",tags = {"BrandController"})
        @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<Brand>> findPage(@PathVariable Integer page, @PathVariable Integer size){
        //调用BrandService实现分页查询Brand
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /**
     * Brand分页条件搜索
     * @param brand 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @ApiOperation(value = "Brand条件分页查询",notes = "分页条件查询Brand方法详情",tags = {"BrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo<Brand>> findPage(@RequestBody(required = false) @ApiParam(name = "Brand对象",value = "传入JSON数据",required = false) Brand brand, @PathVariable  Integer page, @PathVariable  Integer size){
        //调用BrandService实现分页条件查询Brand
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", pageInfo);
    }
}
