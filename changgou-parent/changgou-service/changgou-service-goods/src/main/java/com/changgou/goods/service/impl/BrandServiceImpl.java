package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Brand业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class BrandServiceImpl implements BrandService {

    /**
     * Brand持久层接口
     */
    @Autowired
    private BrandMapper brandMapper;

    /**
     *
     * @param cid Category分类id
     * @return 品牌集合
     */
    @Override
    public List<Brand> findByCategory(Integer cid) {
        return brandMapper.findByCategory(cid);
    }

    /**
     * 查询Brand全部数据
     * @return 查询结果
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 根据ID查询Brand
     * @param id Brand id
     * @return 查询结果
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Brand
     * @param brand Brand实体类
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 修改Brand
     * @param brand Brand实体类
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除
     * @param id Brand id
     */
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * Brand构建查询对象
     * @param brand 查询条件
     * @return 条件构造器
     */
    public Example createExample(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            // 
            if (!StringUtils.isEmpty(brand.getId())) {
                criteria.andEqualTo("id", brand.getId());
            }
            // 品牌名称
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            // 品牌图片
            if (!StringUtils.isEmpty(brand.getImage())) {
                criteria.andEqualTo("image", brand.getImage());
            }
            // 品牌首字母
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
            // 品牌排序
            if (!StringUtils.isEmpty(brand.getSeq())) {
                criteria.andEqualTo("seq", brand.getSeq());
            }
        }
        return example;
    }

    /**
     * Brand条件查询
     * @param brand 查询条件
     * @return 查询结果
     */
    @Override
    public List<Brand> findByCategory(Brand brand) {
        // 构建查询条件
        Example example = createExample(brand);
        // 根据构建的条件查询数据
        return brandMapper.selectByExample(example);
    }

    /**
     * Brand分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<>(brandMapper.selectAll());
    }

    /**
     * Brand条件 + 分页查询
     * @param brand 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(brand);
        // 执行搜索
        return new PageInfo<>(brandMapper.selectByExample(example));
    }

}
