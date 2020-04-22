package com.changgou.user.service.impl;

import com.changgou.user.dao.ProvinceMapper;
import com.changgou.user.pojo.Province;
import com.changgou.user.service.ProvinceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Province业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    /**
     * Province持久层接口
     */
    @Autowired
    private ProvinceMapper provinceMapper;

    /**
     * 查询Province全部数据
     * @return 查询结果
     */
    @Override
    public List<Province> findAll() {
        return provinceMapper.selectAll();
    }

    /**
     * 根据ID查询Province
     * @param id Province id
     * @return 查询结果
     */
    @Override
    public Province findById(Integer id){
        return provinceMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Province
     * @param province Province实体类
     */
    @Override
    public void add(Province province){
        provinceMapper.insertSelective(province);
    }

    /**
     * 修改Province
     * @param province Province实体类
     */
    @Override
    public void update(Province province){
        provinceMapper.updateByPrimaryKeySelective(province);
    }

    /**
     * 删除
     * @param id Province id
     */
    @Override
    public void delete(Integer id){
        provinceMapper.deleteByPrimaryKey(id);
    }

    /**
     * Province构建查询对象
     * @param province 查询条件
     * @return 条件构造器
     */
    public Example createExample(Province province){
        Example example=new Example(Province.class);
        Example.Criteria criteria = example.createCriteria();
        if(province!=null){
            // 省份id
            if(!StringUtils.isEmpty(province.getId())){
                    criteria.andEqualTo("id",province.getId());
            }
            // 省份名称
            if(!StringUtils.isEmpty(province.getName())){
                    criteria.andLike("name","%"+province.getName()+"%");
            }
        }
        return example;
    }

    /**
     * Province条件查询
     * @param province 查询条件
     * @return 查询结果
     */
    @Override
    public List<Province> findList(Province province){
        // 构建查询条件
        Example example = createExample(province);
        // 根据构建的条件查询数据
        return provinceMapper.selectByExample(example);
    }

    /**
     * Province分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Province> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(provinceMapper.selectAll());
    }

    /**
     * Province条件 + 分页查询
     * @param province 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Province> findPage(Province province, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(province);
        // 执行搜索
        return new PageInfo<>(provinceMapper.selectByExample(example));
    }

}
