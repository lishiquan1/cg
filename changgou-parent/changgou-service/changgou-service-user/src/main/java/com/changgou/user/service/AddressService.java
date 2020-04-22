package com.changgou.user.service;

import com.changgou.user.pojo.Address;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Address业务层接口
 *
 * @author lishiquan
 */
public interface AddressService {
    
    /**
     * 查询所有Address
     * @return 查询结果
     */
    List<Address> findAll();

    /**
     * 根据ID查询Address
     * @param id Address id
     * @return 查询结果
     */
    Address findById(Integer id);


    /**
     * 新增Address
     * @param address Address实体类
     */
    void add(Address address);


    /**
     * 修改Address数据
     * @param address Address实体类
     */
    void update(Address address);

    /**
     * 删除Address
     * @param id Address id
     */
    void delete(Integer id);


    /**
     * Address多条件搜索方法
     * @param address 查询条件
     * @return 查询结果
     */
    List<Address> findList(Address address);

    /**
     * Address分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Address> findPage(Integer page, Integer size);

    /**
     * Address条件查询 + 分页
     * @param address 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Address> findPage(Address address, Integer page, Integer size);

    /**
     * 根据用户id查询收件地址列表
     * @param userId 用户id
     * @return 地址结果集
     */
    List<Address> list(Integer userId);
}
