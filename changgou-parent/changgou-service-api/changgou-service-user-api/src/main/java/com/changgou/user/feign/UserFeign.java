package com.changgou.user.feign;

import com.changgou.common.entity.Result;
import com.changgou.user.pojo.User;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Demo User Feign接口
 *
 * @author lishiquan
 */
@FeignClient(name = "user")
@RequestMapping("/user")
public interface UserFeign {
    /**
     * 增加用户积分
     * @return 积分增加成功
     */
    @GetMapping("/points/add")
    Result addPoints(@RequestParam Integer points);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping("/name/{username}")
    Result<User> findByName(@PathVariable String username);

    /**
     * 查询User全部数据
     * @return 查询结果
     */
    @GetMapping
    Result<List<User>> findAll();

    /**
     * 根据ID查询User数据
     * @param id User id
     * @return 查询结果
     */
    @GetMapping({"/{id}"})
    Result<User> findById(@PathVariable Integer id);

    /**
     * 新增User数据
     * @param user User实体类
     * @return 新增
     */
    @PostMapping
    Result add(@RequestBody User user);

    /**
     * 修改User数据
     * @param user User实体类
     * @param id User id
     * @return 修改
     */
    @PutMapping(value = "/{id}")
    Result update(@RequestBody User user, @PathVariable Integer id);

    /**
     * 根据ID删除数据
     * @param id User id
     * @return 删除
     */
    @DeleteMapping(value = "/{id}")
    Result<User> delete(@PathVariable Integer id);

    /**
     * 多条件查询
     * @param user 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/search")
    Result<List<User>> findList(@RequestBody(required = false) User user);

    /**
     * User分页搜索
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @GetMapping(value = "/search/{page}/{size}")
    Result<PageInfo<User>> findPage(@PathVariable Integer page, @PathVariable Integer size);

    /**
     * User条件搜索 + 分页
     * @param user 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value = "/search/{page}/{size}")
    Result<PageInfo<User>> findPage(@RequestBody(required = false) User user, @PathVariable Integer page, @PathVariable Integer size);
}