package com.changgou.user.service.impl;

import com.changgou.user.dao.CityMapper;
import com.changgou.user.pojo.City;
import com.changgou.user.service.CityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo City业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class CityServiceImpl implements CityService {

    /**
     * City持久层接口
     */
    @Autowired
    private CityMapper cityMapper;

    /**
     * 查询City全部数据
     * @return 查询结果
     */
    @Override
    public List<City> findAll() {
        return cityMapper.selectAll();
    }

    /**
     * 根据ID查询City
     * @param id City id
     * @return 查询结果
     */
    @Override
    public City findById(Integer id){
        return cityMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加City
     * @param city City实体类
     */
    @Override
    public void add(City city){
        cityMapper.insertSelective(city);
    }

    /**
     * 修改City
     * @param city City实体类
     */
    @Override
    public void update(City city){
        cityMapper.updateByPrimaryKeySelective(city);
    }

    /**
     * 删除
     * @param id City id
     */
    @Override
    public void delete(Integer id){
        cityMapper.deleteByPrimaryKey(id);
    }

    /**
     * City构建查询对象
     * @param city 查询条件
     * @return 条件构造器
     */
    public Example createExample(City city){
        Example example=new Example(City.class);
        Example.Criteria criteria = example.createCriteria();
        if(city!=null){
            // 城市id
            if(!StringUtils.isEmpty(city.getId())){
                    criteria.andEqualTo("id",city.getId());
            }
            // 城市名称
            if(!StringUtils.isEmpty(city.getName())){
                    criteria.andLike("name","%"+city.getName()+"%");
            }
            // 省份id
            if(!StringUtils.isEmpty(city.getProvinceId())){
                    criteria.andEqualTo("provinceId",city.getProvinceId());
            }
        }
        return example;
    }

    /**
     * City条件查询
     * @param city 查询条件
     * @return 查询结果
     */
    @Override
    public List<City> findList(City city){
        // 构建查询条件
        Example example = createExample(city);
        // 根据构建的条件查询数据
        return cityMapper.selectByExample(example);
    }

    /**
     * City分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<City> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(cityMapper.selectAll());
    }

    /**
     * City条件 + 分页查询
     * @param city 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<City> findPage(City city, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(city);
        // 执行搜索
        return new PageInfo<>(cityMapper.selectByExample(example));
    }

}
