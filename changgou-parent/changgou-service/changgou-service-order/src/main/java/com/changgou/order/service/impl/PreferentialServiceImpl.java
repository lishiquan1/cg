package com.changgou.order.service.impl;

import com.changgou.order.dao.PreferentialMapper;
import com.changgou.order.pojo.Preferential;
import com.changgou.order.service.PreferentialService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Preferential业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class PreferentialServiceImpl implements PreferentialService {

    /**
     * Preferential持久层接口
     */
    @Autowired
    private PreferentialMapper preferentialMapper;

    /**
     * 查询Preferential全部数据
     * @return 查询结果
     */
    @Override
    public List<Preferential> findAll() {
        return preferentialMapper.selectAll();
    }

    /**
     * 根据ID查询Preferential
     * @param id Preferential id
     * @return 查询结果
     */
    @Override
    public Preferential findById(Integer id){
        return preferentialMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Preferential
     * @param preferential Preferential实体类
     */
    @Override
    public void add(Preferential preferential){
        preferentialMapper.insertSelective(preferential);
    }

    /**
     * 修改Preferential
     * @param preferential Preferential实体类
     */
    @Override
    public void update(Preferential preferential){
        preferentialMapper.updateByPrimaryKeySelective(preferential);
    }

    /**
     * 删除
     * @param id Preferential id
     */
    @Override
    public void delete(Integer id){
        preferentialMapper.deleteByPrimaryKey(id);
    }

    /**
     * Preferential构建查询对象
     * @param preferential 查询条件
     * @return 条件构造器
     */
    public Example createExample(Preferential preferential){
        Example example=new Example(Preferential.class);
        Example.Criteria criteria = example.createCriteria();
        if(preferential!=null){
            // ID
            if(!StringUtils.isEmpty(preferential.getId())){
                    criteria.andEqualTo("id",preferential.getId());
            }
            // 消费金额
            if(!StringUtils.isEmpty(preferential.getBuyMoney())){
                    criteria.andEqualTo("buyMoney",preferential.getBuyMoney());
            }
            // 优惠金额
            if(!StringUtils.isEmpty(preferential.getPreMoney())){
                    criteria.andEqualTo("preMoney",preferential.getPreMoney());
            }
            // 分类ID
            if(!StringUtils.isEmpty(preferential.getCategoryId())){
                    criteria.andEqualTo("categoryId",preferential.getCategoryId());
            }
            // 活动开始时间
            if(!StringUtils.isEmpty(preferential.getStartTime())){
                    criteria.andEqualTo("startTime",preferential.getStartTime());
            }
            // 活动结束时间
            if(!StringUtils.isEmpty(preferential.getEndTime())){
                    criteria.andEqualTo("endTime",preferential.getEndTime());
            }
            // 状态
            if(!StringUtils.isEmpty(preferential.getState())){
                    criteria.andEqualTo("state",preferential.getState());
            }
            // 类型: 1 不翻倍, 2 翻倍
            if(!StringUtils.isEmpty(preferential.getType())){
                    criteria.andEqualTo("type",preferential.getType());
            }
        }
        return example;
    }

    /**
     * Preferential条件查询
     * @param preferential 查询条件
     * @return 查询结果
     */
    @Override
    public List<Preferential> findList(Preferential preferential){
        // 构建查询条件
        Example example = createExample(preferential);
        // 根据构建的条件查询数据
        return preferentialMapper.selectByExample(example);
    }

    /**
     * Preferential分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Preferential> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(preferentialMapper.selectAll());
    }

    /**
     * Preferential条件 + 分页查询
     * @param preferential 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Preferential> findPage(Preferential preferential, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(preferential);
        // 执行搜索
        return new PageInfo<>(preferentialMapper.selectByExample(example));
    }

}
