package com.changgou.user.feign;

import com.changgou.common.entity.Result;
import com.changgou.user.pojo.Province;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Province Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "user")
@RequestMapping("/province")
public interface ProvinceFeign {

    /**
     * 查询Province全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<Province>> findAll();

    /**
     * 根据ID查询Province数据
     * @param id Province id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<Province> findById(@PathVariable Integer id);

    /**
     * 新增Province数据
     * @param province Province实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody Province province);

    /**
     * 修改Province数据
     * @param province Province实体类
     * @param id Province id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Province province,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id Province id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<Province> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param province 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<Province>> findList(@RequestBody(required = false) Province province);

    /**
     * Province分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Province>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * Province条件搜索 + 分页
     * @param province 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Province>> findPage(@RequestBody(required = false) Province province, @PathVariable Integer page, @PathVariable Integer size);
}