package com.changgou.goods.service.impl;

import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.ParaMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.ParaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Para业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class ParaServiceImpl implements ParaService {

    /**
     * Para持久层接口
     */
    @Autowired
    private ParaMapper paraMapper;

    /**
     * Category持久层接口
     */
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分类id查询模板id, 根据模板id查询参数集合
     * @param cid 分类di
     * @return 参数集合
     */
    @Override
    public List<Para> findByCategory(Integer cid) {
        // 获取分了信息
        Category category = categoryMapper.selectByPrimaryKey(cid);
        // 根据模板id查询参数集合
        Para para = new Para();
        para.setTemplateId(category.getTemplateId());
        List<Para> list = paraMapper.select(para);
        return list;
    }

    /**
     * 查询Para全部数据
     * @return 查询结果
     */
    @Override
    public List<Para> findAll() {
        return paraMapper.selectAll();
    }

    /**
     * 根据ID查询Para
     * @param id Para id
     * @return 查询结果
     */
    @Override
    public Para findById(Integer id) {
        return paraMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Para
     * @param para Para实体类
     */
    @Override
    public void add(Para para) {
        paraMapper.insertSelective(para);
    }

    /**
     * 修改Para
     * @param para Para实体类
     */
    @Override
    public void update(Para para) {
        paraMapper.updateByPrimaryKeySelective(para);
    }

    /**
     * 删除
     * @param id Para id
     */
    @Override
    public void delete(Integer id) {
        paraMapper.deleteByPrimaryKey(id);
    }

    /**
     * Para构建查询对象
     * @param para 查询条件
     * @return 条件构造器
     */
    public Example createExample(Para para) {
        Example example = new Example(Para.class);
        Example.Criteria criteria = example.createCriteria();
        if (para != null) {
            // 
            if (!StringUtils.isEmpty(para.getId())) {
                criteria.andEqualTo("id", para.getId());
            }
            // 参数名称
            if (!StringUtils.isEmpty(para.getName())) {
                criteria.andLike("name", "%" + para.getName() + "%");
            }
            // 选项
            if (!StringUtils.isEmpty(para.getOptions())) {
                criteria.andEqualTo("options", para.getOptions());
            }
            // 排序
            if (!StringUtils.isEmpty(para.getSeq())) {
                criteria.andEqualTo("seq", para.getSeq());
            }
            // 模板id
            if (!StringUtils.isEmpty(para.getTemplateId())) {
                criteria.andEqualTo("templateId", para.getTemplateId());
            }
        }
        return example;
    }

    /**
     * Para条件查询
     * @param para 查询条件
     * @return 查询结果
     */
    @Override
    public List<Para> findList(Para para) {
        // 构建查询条件
        Example example = createExample(para);
        // 根据构建的条件查询数据
        return paraMapper.selectByExample(example);
    }

    /**
     * Para分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Para> findPage(Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<>(paraMapper.selectAll());
    }

    /**
     * Para条件 + 分页查询
     * @param para 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Para> findPage(Para para, Integer page, Integer size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(para);
        // 执行搜索
        return new PageInfo<>(paraMapper.selectByExample(example));
    }

}
