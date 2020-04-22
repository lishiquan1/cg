package com.changgou.goods.service;

import com.changgou.goods.pojo.Template;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Template业务层接口
 *
 * @author lishiquan
 */
public interface TemplateService {
    
    /**
     * 查询所有Template
     * @return 查询结果
     */
    List<Template> findAll();

    /**
     * 根据ID查询Template
     * @param id Template id
     * @return 查询结果
     */
    Template findById(Integer id);


    /**
     * 新增Template
     * @param template Template实体类
     */
    void add(Template template);


    /**
     * 修改Template数据
     * @param template Template实体类
     */
    void update(Template template);

    /**
     * 删除Template
     * @param id Template id
     */
    void delete(Integer id);


    /**
     * Template多条件搜索方法
     * @param template 查询条件
     * @return 查询结果
     */
    List<Template> findList(Template template);

    /**
     * Template分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Template> findPage(Integer page, Integer size);

    /**
     * Template条件查询 + 分页
     * @param template 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Template> findPage(Template template, Integer page, Integer size);

}
