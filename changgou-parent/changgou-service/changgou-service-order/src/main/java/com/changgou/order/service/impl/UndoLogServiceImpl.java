package com.changgou.order.service.impl;

import com.changgou.order.dao.UndoLogMapper;
import com.changgou.order.pojo.UndoLog;
import com.changgou.order.service.UndoLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo UndoLog业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class UndoLogServiceImpl implements UndoLogService {

    /**
     * UndoLog持久层接口
     */
    @Autowired
    private UndoLogMapper undoLogMapper;

    /**
     * 查询UndoLog全部数据
     * @return 查询结果
     */
    @Override
    public List<UndoLog> findAll() {
        return undoLogMapper.selectAll();
    }

    /**
     * 根据ID查询UndoLog
     * @param id UndoLog id
     * @return 查询结果
     */
    @Override
    public UndoLog findById(Integer id){
        return undoLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加UndoLog
     * @param undoLog UndoLog实体类
     */
    @Override
    public void add(UndoLog undoLog){
        undoLogMapper.insertSelective(undoLog);
    }

    /**
     * 修改UndoLog
     * @param undoLog UndoLog实体类
     */
    @Override
    public void update(UndoLog undoLog){
        undoLogMapper.updateByPrimaryKeySelective(undoLog);
    }

    /**
     * 删除
     * @param id UndoLog id
     */
    @Override
    public void delete(Integer id){
        undoLogMapper.deleteByPrimaryKey(id);
    }

    /**
     * UndoLog构建查询对象
     * @param undoLog 查询条件
     * @return 条件构造器
     */
    public Example createExample(UndoLog undoLog){
        Example example=new Example(UndoLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(undoLog!=null){
            // ID
            if(!StringUtils.isEmpty(undoLog.getId())){
                    criteria.andEqualTo("id",undoLog.getId());
            }
            // 分支id
            if(!StringUtils.isEmpty(undoLog.getBranchId())){
                    criteria.andEqualTo("branchId",undoLog.getBranchId());
            }
            // 
            if(!StringUtils.isEmpty(undoLog.getXid())){
                    criteria.andEqualTo("xid",undoLog.getXid());
            }
            // 回滚
            if(!StringUtils.isEmpty(undoLog.getRollbackInfo())){
                    criteria.andEqualTo("rollbackInfo",undoLog.getRollbackInfo());
            }
            // 创建时间
            if(!StringUtils.isEmpty(undoLog.getLogCreated())){
                    criteria.andEqualTo("logCreated",undoLog.getLogCreated());
            }
            // 
            if(!StringUtils.isEmpty(undoLog.getLogModified())){
                    criteria.andEqualTo("logModified",undoLog.getLogModified());
            }
            // 
            if(!StringUtils.isEmpty(undoLog.getExt())){
                    criteria.andEqualTo("ext",undoLog.getExt());
            }
        }
        return example;
    }

    /**
     * UndoLog条件查询
     * @param undoLog 查询条件
     * @return 查询结果
     */
    @Override
    public List<UndoLog> findList(UndoLog undoLog){
        // 构建查询条件
        Example example = createExample(undoLog);
        // 根据构建的条件查询数据
        return undoLogMapper.selectByExample(example);
    }

    /**
     * UndoLog分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<UndoLog> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(undoLogMapper.selectAll());
    }

    /**
     * UndoLog条件 + 分页查询
     * @param undoLog 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<UndoLog> findPage(UndoLog undoLog, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(undoLog);
        // 执行搜索
        return new PageInfo<>(undoLogMapper.selectByExample(example));
    }

}
