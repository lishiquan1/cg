package com.changgou.order.service.impl;

import com.changgou.order.dao.ReturnCauseMapper;
import com.changgou.order.pojo.ReturnCause;
import com.changgou.order.service.ReturnCauseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo ReturnCause业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class ReturnCauseServiceImpl implements ReturnCauseService {

    /**
     * ReturnCause持久层接口
     */
    @Autowired
    private ReturnCauseMapper returnCauseMapper;

    /**
     * 查询ReturnCause全部数据
     * @return 查询结果
     */
    @Override
    public List<ReturnCause> findAll() {
        return returnCauseMapper.selectAll();
    }

    /**
     * 根据ID查询ReturnCause
     * @param id ReturnCause id
     * @return 查询结果
     */
    @Override
    public ReturnCause findById(Integer id){
        return returnCauseMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加ReturnCause
     * @param returnCause ReturnCause实体类
     */
    @Override
    public void add(ReturnCause returnCause){
        returnCauseMapper.insertSelective(returnCause);
    }

    /**
     * 修改ReturnCause
     * @param returnCause ReturnCause实体类
     */
    @Override
    public void update(ReturnCause returnCause){
        returnCauseMapper.updateByPrimaryKeySelective(returnCause);
    }

    /**
     * 删除
     * @param id ReturnCause id
     */
    @Override
    public void delete(Integer id){
        returnCauseMapper.deleteByPrimaryKey(id);
    }

    /**
     * ReturnCause构建查询对象
     * @param returnCause 查询条件
     * @return 条件构造器
     */
    public Example createExample(ReturnCause returnCause){
        Example example=new Example(ReturnCause.class);
        Example.Criteria criteria = example.createCriteria();
        if(returnCause!=null){
            // ID
            if(!StringUtils.isEmpty(returnCause.getId())){
                    criteria.andEqualTo("id",returnCause.getId());
            }
            // 原因
            if(!StringUtils.isEmpty(returnCause.getCause())){
                    criteria.andEqualTo("cause",returnCause.getCause());
            }
            // 排序
            if(!StringUtils.isEmpty(returnCause.getSeq())){
                    criteria.andEqualTo("seq",returnCause.getSeq());
            }
            // 是否启用
            if(!StringUtils.isEmpty(returnCause.getStatus())){
                    criteria.andEqualTo("status",returnCause.getStatus());
            }
        }
        return example;
    }

    /**
     * ReturnCause条件查询
     * @param returnCause 查询条件
     * @return 查询结果
     */
    @Override
    public List<ReturnCause> findList(ReturnCause returnCause){
        // 构建查询条件
        Example example = createExample(returnCause);
        // 根据构建的条件查询数据
        return returnCauseMapper.selectByExample(example);
    }

    /**
     * ReturnCause分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<ReturnCause> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(returnCauseMapper.selectAll());
    }

    /**
     * ReturnCause条件 + 分页查询
     * @param returnCause 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<ReturnCause> findPage(ReturnCause returnCause, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(returnCause);
        // 执行搜索
        return new PageInfo<>(returnCauseMapper.selectByExample(example));
    }

}
