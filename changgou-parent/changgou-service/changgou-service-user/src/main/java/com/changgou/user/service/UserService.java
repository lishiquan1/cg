package com.changgou.user.service;

import com.changgou.user.pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo User业务层接口
 *
 * @author lishiquan
 */
public interface UserService {
    /**
     * 登录验证
     * @param username 用户名
     * @return 用户信息
     */
    User login(String username);


    /**
     * 查询所有User
     * @return 查询结果
     */
    List<User> findAll();

    /**
     * 根据ID查询User
     * @param id User id
     * @return 查询结果
     */
    User findById(Integer id);


    /**
     * 新增User
     * @param user User实体类
     */
    void add(User user);


    /**
     * 修改User数据
     * @param user User实体类
     */
    void update(User user);

    /**
     * 删除User
     * @param id User id
     */
    void delete(Integer id);


    /**
     * User多条件搜索方法
     * @param user 查询条件
     * @return 查询结果
     */
    List<User> findList(User user);

    /**
     * User分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<User> findPage(Integer page, Integer size);

    /**
     * User条件查询 + 分页
     * @param user 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<User> findPage(User user, Integer page, Integer size);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    User findByName(String username);

    /**
     * 增加用户积分
     * @param id 用户id
     * @param points 积分
     */
    void addPoints(Integer id, Integer points);
}
