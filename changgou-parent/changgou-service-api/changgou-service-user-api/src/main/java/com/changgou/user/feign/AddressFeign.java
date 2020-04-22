package com.changgou.user.feign;

import com.changgou.common.entity.Result;
import com.changgou.user.pojo.Address;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo Address Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "user")
@RequestMapping("/address")
public interface AddressFeign {

    /**
     * 查询Address全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<Address>> findAll();

    /**
     * 根据ID查询Address数据
     * @param id Address id
     * @return 查询结果
     */
    @GetMapping("/{id}")
    Result<Address> findById(@PathVariable Integer id);

    /**
     * 新增Address数据
     * @param address Address实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody Address address);

    /**
     * 修改Address数据
     * @param address Address实体类
     * @param id Address id
     * @return 修改
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Address address,@PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id Address id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}" )
    Result<Address> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param address 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search" )
    Result<List<Address>> findList(@RequestBody(required = false) Address address);

    /**
     * Address分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Address>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * Address条件搜索 + 分页
     * @param address 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo<Address>> findPage(@RequestBody(required = false) Address address, @PathVariable Integer page, @PathVariable Integer size);
}