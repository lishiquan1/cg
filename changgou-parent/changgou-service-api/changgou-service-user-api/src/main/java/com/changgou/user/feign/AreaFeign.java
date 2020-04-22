package com.changgou.user.feign;

import com.changgou.common.entity.Result;
import com.changgou.user.pojo.Area;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Area Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "user")
@RequestMapping("/area")
public interface AreaFeign {

    /**
     * 查询Area全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<Area>> findAll();

    /**
     * 根据ID查询Area数据
     * @param id Area id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<Area> findById(@PathVariable Integer id);

    /**
     * 新增Area数据
     * @param area Area实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody Area area);

    /**
     * 修改Area数据
     * @param area Area实体类
     * @param id Area id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Area area,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id Area id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<Area> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param area 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<Area>> findList(@RequestBody(required = false) Area area);

    /**
     * Area分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Area>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * Area条件搜索 + 分页
     * @param area 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Area>> findPage(@RequestBody(required = false) Area area, @PathVariable Integer page, @PathVariable Integer size);
}