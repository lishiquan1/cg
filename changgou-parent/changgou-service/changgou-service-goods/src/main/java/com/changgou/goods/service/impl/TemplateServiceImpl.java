package com.changgou.goods.service.impl;

import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Template业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    /**
     * Template持久层接口
     */
    @Autowired
    private TemplateMapper templateMapper;

    /**
     * 查询Template全部数据
     * @return 查询结果
     */
    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }

    /**
     * 根据ID查询Template
     * @param id Template id
     * @return 查询结果
     */
    @Override
    public Template findById(Integer id){
        return  templateMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Template
     * @param template Template实体类
     */
    @Override
    public void add(Template template){
        templateMapper.insertSelective(template);
    }

    /**
     * 修改Template
     * @param template Template实体类
     */
    @Override
    public void update(Template template){
        templateMapper.updateByPrimaryKeySelective(template);
    }

    /**
     * 删除
     * @param id Template id
     */
    @Override
    public void delete(Integer id){
        templateMapper.deleteByPrimaryKey(id);
    }

    /**
     * Template构建查询对象
     * @param template 查询条件
     * @return 条件构造器
     */
    public Example createExample(Template template){
        Example example=new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if(template!=null){
            // 
            if(!StringUtils.isEmpty(template.getId())){
                    criteria.andEqualTo("id",template.getId());
            }
            // 模板名称
            if(!StringUtils.isEmpty(template.getName())){
                    criteria.andLike("name","%"+template.getName()+"%");
            }
            // 规格数量
            if(!StringUtils.isEmpty(template.getSpecNum())){
                    criteria.andEqualTo("specNum",template.getSpecNum());
            }
            // 参数数量
            if(!StringUtils.isEmpty(template.getParNum())){
                    criteria.andEqualTo("parNum",template.getParNum());
            }
        }
        return example;
    }

    /**
     * Template条件查询
     * @param template 查询条件
     * @return 查询结果
     */
    @Override
    public List<Template> findList(Template template){
        // 构建查询条件
        Example example = createExample(template);
        // 根据构建的条件查询数据
        return templateMapper.selectByExample(example);
    }

    /**
     * Template分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Template> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(templateMapper.selectAll());
    }

    /**
     * Template条件 + 分页查询
     * @param template 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Template> findPage(Template template, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(template);
        // 执行搜索
        return new PageInfo<>(templateMapper.selectByExample(example));
    }

}
