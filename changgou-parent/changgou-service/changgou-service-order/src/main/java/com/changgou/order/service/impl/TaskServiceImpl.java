package com.changgou.order.service.impl;

import com.changgou.order.dao.TaskMapper;
import com.changgou.order.pojo.Task;
import com.changgou.order.service.TaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Task业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class TaskServiceImpl implements TaskService {

    /**
     * Task持久层接口
     */
    @Autowired
    private TaskMapper taskMapper;

    /**
     * 查询Task全部数据
     * @return 查询结果
     */
    @Override
    public List<Task> findAll() {
        return taskMapper.selectAll();
    }

    /**
     * 根据ID查询Task
     * @param id Task id
     * @return 查询结果
     */
    @Override
    public Task findById(Integer id){
        return taskMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Task
     * @param task Task实体类
     */
    @Override
    public void add(Task task){
        taskMapper.insertSelective(task);
    }

    /**
     * 修改Task
     * @param task Task实体类
     */
    @Override
    public void update(Task task){
        taskMapper.updateByPrimaryKeySelective(task);
    }

    /**
     * 删除
     * @param id Task id
     */
    @Override
    public void delete(Integer id){
        taskMapper.deleteByPrimaryKey(id);
    }

    /**
     * Task构建查询对象
     * @param task 查询条件
     * @return 条件构造器
     */
    public Example createExample(Task task){
        Example example=new Example(Task.class);
        Example.Criteria criteria = example.createCriteria();
        if(task!=null){
            // ID
            if(!StringUtils.isEmpty(task.getId())){
                    criteria.andEqualTo("id",task.getId());
            }
            // 创建时间
            if(!StringUtils.isEmpty(task.getCreateTime())){
                    criteria.andEqualTo("createTime",task.getCreateTime());
            }
            // 修改时间
            if(!StringUtils.isEmpty(task.getUpdateTime())){
                    criteria.andEqualTo("updateTime",task.getUpdateTime());
            }
            // 删除时间
            if(!StringUtils.isEmpty(task.getDeleteTime())){
                    criteria.andEqualTo("deleteTime",task.getDeleteTime());
            }
            // 任务类型
            if(!StringUtils.isEmpty(task.getTaskType())){
                    criteria.andEqualTo("taskType",task.getTaskType());
            }
            // 交换机
            if(!StringUtils.isEmpty(task.getMqExchange())){
                    criteria.andEqualTo("mqExchange",task.getMqExchange());
            }
            // 路由键
            if(!StringUtils.isEmpty(task.getMqRoutingkey())){
                    criteria.andEqualTo("mqRoutingkey",task.getMqRoutingkey());
            }
            // 消息体
            if(!StringUtils.isEmpty(task.getRequestBody())){
                    criteria.andEqualTo("requestBody",task.getRequestBody());
            }
            // 状态
            if(!StringUtils.isEmpty(task.getStatus())){
                    criteria.andEqualTo("status",task.getStatus());
            }
            // 错误信息
            if(!StringUtils.isEmpty(task.getErrormsg())){
                    criteria.andEqualTo("errormsg",task.getErrormsg());
            }
        }
        return example;
    }

    /**
     * Task条件查询
     * @param task 查询条件
     * @return 查询结果
     */
    @Override
    public List<Task> findList(Task task){
        // 构建查询条件
        Example example = createExample(task);
        // 根据构建的条件查询数据
        return taskMapper.selectByExample(example);
    }

    /**
     * Task分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Task> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(taskMapper.selectAll());
    }

    /**
     * Task条件 + 分页查询
     * @param task 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Task> findPage(Task task, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(task);
        // 执行搜索
        return new PageInfo<>(taskMapper.selectByExample(example));
    }

}
