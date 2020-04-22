package com.changgou.goods.service.impl;

import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Category业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    /**
     * Category持久层接口
     */
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据分类的父节点id查询所有子节点集合
     * @param pid 父节点ID -> 1级分类 = 0
     * @return 查询结果
     */
    @Override
    public List<Category> findByParentId(Integer pid) {
        // 封装一个JavaBean, 如果该JavaBean指定属性不为null, 则会将指定属性作为查询条件
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }

    /**
     * 查询Category全部数据
     * @return 查询结果
     */
    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    /**
     * 根据ID查询Category
     * @param id Category id
     * @return 查询结果
     */
    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Category
     * @param category Category实体类
     */
    @Override
    public void add(Category category) {
        categoryMapper.insertSelective(category);
    }

    /**
     * 修改Category
     * @param category Category实体类
     */
    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    /**
     * 删除
     * @param id Category id
     */
    @Override
    public void delete(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * Category构建查询对象
     * @param category 查询条件
     * @return 条件构造器
     */
    public Example createExample(Category category) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if (category != null) {
            // 
            if (!StringUtils.isEmpty(category.getId())) {
                criteria.andEqualTo("id", category.getId());
            }
            // 分类名称
            if (!StringUtils.isEmpty(category.getName())) {
                criteria.andLike("name", "%" + category.getName() + "%");
            }
            // 商品数量
            if (!StringUtils.isEmpty(category.getGoodsNum())) {
                criteria.andEqualTo("goodsNum", category.getGoodsNum());
            }
            // 是否显示
            if (!StringUtils.isEmpty(category.getIsShow())) {
                criteria.andEqualTo("isShow", category.getIsShow());
            }
            // 是否导航
            if (!StringUtils.isEmpty(category.getIsMenu())) {
                criteria.andEqualTo("isMenu", category.getIsMenu());
            }
            // 排序
            if (!StringUtils.isEmpty(category.getSeq())) {
                criteria.andEqualTo("seq", category.getSeq());
            }
            // 上级id,0表示是1级分类
            if (!StringUtils.isEmpty(category.getParentId())) {
                criteria.andEqualTo("parentId", category.getParentId());
            }
            // 模板id
            if (!StringUtils.isEmpty(category.getTemplateId())) {
                criteria.andEqualTo("templateId", category.getTemplateId());
            }
        }
        return example;
    }

    /**
     * Category条件查询
     * @param category 查询条件
     * @return 查询结果
     */
    @Override
    public List<Category> findList(Category category) {
        // 构建查询条件
        Example example = createExample(category);
        // 根据构建的条件查询数据
        return categoryMapper.selectByExample(example);
    }

    /**
     * Category分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Category> findPage(Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<>(categoryMapper.selectAll());
    }

    /**
     * Category条件 + 分页查询
     * @param category 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Category> findPage(Category category, Integer page, Integer size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(category);
        // 执行搜索
        return new PageInfo<>(categoryMapper.selectByExample(example));
    }

}
