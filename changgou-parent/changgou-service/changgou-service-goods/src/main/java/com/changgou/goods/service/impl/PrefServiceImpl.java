package com.changgou.goods.service.impl;

import com.changgou.goods.dao.PrefMapper;
import com.changgou.goods.pojo.Pref;
import com.changgou.goods.service.PrefService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Pref业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class PrefServiceImpl implements PrefService {

    /**
     * Pref持久层接口
     */
    @Autowired
    private PrefMapper prefMapper;

    /**
     * 查询Pref全部数据
     * @return 查询结果
     */
    @Override
    public List<Pref> findAll() {
        return prefMapper.selectAll();
    }

    /**
     * 根据ID查询Pref
     * @param id Pref id
     * @return 查询结果
     */
    @Override
    public Pref findById(Integer id){
        return  prefMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Pref
     * @param pref Pref实体类
     */
    @Override
    public void add(Pref pref){
        prefMapper.insertSelective(pref);
    }

    /**
     * 修改Pref
     * @param pref Pref实体类
     */
    @Override
    public void update(Pref pref){
        prefMapper.updateByPrimaryKeySelective(pref);
    }

    /**
     * 删除
     * @param id Pref id
     */
    @Override
    public void delete(Integer id){
        prefMapper.deleteByPrimaryKey(id);
    }

    /**
     * Pref构建查询对象
     * @param pref 查询条件
     * @return 条件构造器
     */
    public Example createExample(Pref pref){
        Example example=new Example(Pref.class);
        Example.Criteria criteria = example.createCriteria();
        if(pref!=null){
            // 
            if(!StringUtils.isEmpty(pref.getId())){
                    criteria.andEqualTo("id",pref.getId());
            }
            // 分类id
            if(!StringUtils.isEmpty(pref.getCategoryId())){
                    criteria.andEqualTo("cateId",pref.getCategoryId());
            }
            // 消费金额
            if(!StringUtils.isEmpty(pref.getBuyMoney())){
                    criteria.andEqualTo("buyMoney",pref.getBuyMoney());
            }
            // 优惠金额
            if(!StringUtils.isEmpty(pref.getPerMoney())){
                    criteria.andEqualTo("perMoney",pref.getPerMoney());
            }
            // 获取开始日期
            if(!StringUtils.isEmpty(pref.getStartTime())){
                    criteria.andEqualTo("startTime",pref.getStartTime());
            }
            // 活动结束日期
            if(!StringUtils.isEmpty(pref.getEndTime())){
                    criteria.andEqualTo("endTime",pref.getEndTime());
            }
            // 类型
            if(!StringUtils.isEmpty(pref.getType())){
                    criteria.andEqualTo("type",pref.getType());
            }
            // 状态
            if(!StringUtils.isEmpty(pref.getState())){
                    criteria.andEqualTo("state",pref.getState());
            }
        }
        return example;
    }

    /**
     * Pref条件查询
     * @param pref 查询条件
     * @return 查询结果
     */
    @Override
    public List<Pref> findList(Pref pref){
        // 构建查询条件
        Example example = createExample(pref);
        // 根据构建的条件查询数据
        return prefMapper.selectByExample(example);
    }

    /**
     * Pref分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Pref> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(prefMapper.selectAll());
    }

    /**
     * Pref条件 + 分页查询
     * @param pref 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Pref> findPage(Pref pref, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(pref);
        // 执行搜索
        return new PageInfo<>(prefMapper.selectByExample(example));
    }

}
