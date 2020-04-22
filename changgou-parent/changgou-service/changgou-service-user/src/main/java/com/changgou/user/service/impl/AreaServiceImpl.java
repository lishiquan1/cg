package com.changgou.user.service.impl;

import com.changgou.user.dao.AreaMapper;
import com.changgou.user.pojo.Area;
import com.changgou.user.service.AreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Area业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class AreaServiceImpl implements AreaService {

    /**
     * Area持久层接口
     */
    @Autowired
    private AreaMapper areaMapper;

    /**
     * 查询Area全部数据
     * @return 查询结果
     */
    @Override
    public List<Area> findAll() {
        return areaMapper.selectAll();
    }

    /**
     * 根据ID查询Area
     * @param id Area id
     * @return 查询结果
     */
    @Override
    public Area findById(Integer id){
        return areaMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Area
     * @param area Area实体类
     */
    @Override
    public void add(Area area){
        areaMapper.insertSelective(area);
    }

    /**
     * 修改Area
     * @param area Area实体类
     */
    @Override
    public void update(Area area){
        areaMapper.updateByPrimaryKeySelective(area);
    }

    /**
     * 删除
     * @param id Area id
     */
    @Override
    public void delete(Integer id){
        areaMapper.deleteByPrimaryKey(id);
    }

    /**
     * Area构建查询对象
     * @param area 查询条件
     * @return 条件构造器
     */
    public Example createExample(Area area){
        Example example=new Example(Area.class);
        Example.Criteria criteria = example.createCriteria();
        if(area!=null){
            // 区域id
            if(!StringUtils.isEmpty(area.getId())){
                    criteria.andEqualTo("id",area.getId());
            }
            // 区域名称
            if(!StringUtils.isEmpty(area.getName())){
                    criteria.andLike("name","%"+area.getName()+"%");
            }
            // 城市id
            if(!StringUtils.isEmpty(area.getCityId())){
                    criteria.andEqualTo("cityId",area.getCityId());
            }
        }
        return example;
    }

    /**
     * Area条件查询
     * @param area 查询条件
     * @return 查询结果
     */
    @Override
    public List<Area> findList(Area area){
        // 构建查询条件
        Example example = createExample(area);
        // 根据构建的条件查询数据
        return areaMapper.selectByExample(example);
    }

    /**
     * Area分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Area> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(areaMapper.selectAll());
    }

    /**
     * Area条件 + 分页查询
     * @param area 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Area> findPage(Area area, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(area);
        // 执行搜索
        return new PageInfo<>(areaMapper.selectByExample(example));
    }

}
