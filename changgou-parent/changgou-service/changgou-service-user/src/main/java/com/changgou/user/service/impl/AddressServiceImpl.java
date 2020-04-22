package com.changgou.user.service.impl;

import com.changgou.user.dao.AddressMapper;
import com.changgou.user.pojo.Address;
import com.changgou.user.service.AddressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Address业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class AddressServiceImpl implements AddressService {

    /**
     * Address持久层接口
     */
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询Address全部数据
     * @return 查询结果
     */
    @Override
    public List<Address> findAll() {
        return addressMapper.selectAll();
    }

    /**
     * 根据ID查询Address
     * @param id Address id
     * @return 查询结果
     */
    @Override
    public Address findById(Integer id){
        return addressMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Address
     * @param address Address实体类
     */
    @Override
    public void add(Address address){
        addressMapper.insertSelective(address);
    }

    /**
     * 修改Address
     * @param address Address实体类
     */
    @Override
    public void update(Address address){
        addressMapper.updateByPrimaryKeySelective(address);
    }

    /**
     * 删除
     * @param id Address id
     */
    @Override
    public void delete(Integer id){
        addressMapper.deleteByPrimaryKey(id);
    }

    /**
     * Address构建查询对象
     * @param address 查询条件
     * @return 条件构造器
     */
    public Example createExample(Address address){
        Example example=new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        if(address!=null){
            // 主键id
            if(!StringUtils.isEmpty(address.getId())){
                    criteria.andEqualTo("id",address.getId());
            }
            // 用户id
            if(!StringUtils.isEmpty(address.getUserId())){
                    criteria.andEqualTo("userId",address.getUserId());
            }
            // 省
            if(!StringUtils.isEmpty(address.getProvinceId())){
                    criteria.andEqualTo("provinceId",address.getProvinceId());
            }
            // 市
            if(!StringUtils.isEmpty(address.getCityId())){
                    criteria.andEqualTo("cityId",address.getCityId());
            }
            // 区/县
            if(!StringUtils.isEmpty(address.getAreaId())){
                    criteria.andEqualTo("areaId",address.getAreaId());
            }
            // 详细地址
            if(!StringUtils.isEmpty(address.getAddress())){
                    criteria.andEqualTo("address",address.getAddress());
            }
            // 电话
            if(!StringUtils.isEmpty(address.getPhone())){
                    criteria.andEqualTo("phone",address.getPhone());
            }
            // 联系人
            if(!StringUtils.isEmpty(address.getConract())){
                    criteria.andEqualTo("conract",address.getConract());
            }
            // 是否默认: 1 是, 0否 
            if(!StringUtils.isEmpty(address.getIsDefault())){
                    criteria.andEqualTo("isDefault",address.getIsDefault());
            }
            // 别名
            if(!StringUtils.isEmpty(address.getAliac())){
                    criteria.andEqualTo("aliac",address.getAliac());
            }
        }
        return example;
    }

    /**
     * Address条件查询
     * @param address 查询条件
     * @return 查询结果
     */
    @Override
    public List<Address> findList(Address address){
        // 构建查询条件
        Example example = createExample(address);
        // 根据构建的条件查询数据
        return addressMapper.selectByExample(example);
    }

    /**
     * Address分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Address> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(addressMapper.selectAll());
    }

    /**
     * Address条件 + 分页查询
     * @param address 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Address> findPage(Address address, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(address);
        // 执行搜索
        return new PageInfo<>(addressMapper.selectByExample(example));
    }

    /**
     * 根据用户id查询收件地址列表
     * @param userId 用户id
     * @return 地址结果集
     */
    @Override
    public List<Address> list(Integer userId) {
        Address address = new Address();
        address.setUserId(userId);
        List<Address> addresses = addressMapper.select(address);
        return addresses;
    }

}
