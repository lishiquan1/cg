package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.pojo.*;
import com.changgou.goods.service.SpuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Demo Spu业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class SpuServiceImpl implements SpuService {

    /**
     * Spu持久层接口
     */
    @Autowired
    private SpuMapper spuMapper;

    /**
     * Sku持久层接口
     */
    @Autowired
    private SkuMapper skuMapper;

    /**
     * Category持久层接口
     */
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * Brand持久层接口
     */
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 批量上架
     * @param ids 商品id组
     */
    @Override
    public void putMany(Integer[] ids) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        // 条件
        criteria.andIn("id", Arrays.asList(ids));
        criteria.andEqualTo("isDelete", "0");
        criteria.andEqualTo("status", "1");
        // 上架
        Spu spu = new Spu();
        spu.setIsMarketable("1");
        spuMapper.updateByExampleSelective(spu, example);
    }

    /**
     * 商品上架
     * @param id Spu id
     */
    @Override
    public void put(Integer id) {
        // 查询商品
        Spu spu = spuMapper.selectByPrimaryKey(id);
        // 判断商品是否符合审核条件
        if ("1".equals(spu.getIsDelete())) {
            throw new RuntimeException("不能对已删除的商品进行上架操作");
        }
        if ("1".equals(spu.getStatus())){
            throw new RuntimeException("该商品未通过审核");
        }
        // 上架状态
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 商品下架
     * @param id Spu id
     */
    @Override
    public void pull(Integer id) {
        // 查询商品
        Spu spu = spuMapper.selectByPrimaryKey(id);
        // 判断商品是否符合审核条件
        if ("1".equals(spu.getIsDelete())) {
            throw new RuntimeException("不能对已删除的商品进行下架操作");
        }
        // 下架状态
        spu.setIsMarketable("0");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 商品审核
     * @param id Spu id
     */
    @Override
    public void audit(Integer id) {
        // 查询商品
        Spu spu = spuMapper.selectByPrimaryKey(id);
        // 判断商品是否符合审核条件
        if ("1".equals(spu.getIsDelete())) {
            throw new RuntimeException("不能对已删除的商品进行审核");
        }
        // 审核状态
        spu.setStatus("1");
        // 上架
        spu.setIsMarketable("1");
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 根据id查询Goods信息
     * @param id Spu id
     * @return 商品信息
     */
    @Override
    public Goods findGoods(Integer id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        List<Sku> skuList = skuMapper.select(sku);
        Goods goods = new Goods();
        goods.setSpu(spu);
        goods.setSkuList(skuList);
        return goods;
    }

    /**
     * 添加/修改 商品信息
     * @param goods 商品信息
     */
    @Override
    public void saveGoods(Goods goods) {
        Spu spu = goods.getSpu();
        // sup id不存在则添加商品, 反之修改商品信息
        if (spu.getId() == null) {
            // 添加spu
            spuMapper.insertSelective(spu);
        } else {
            // 修改spu
            spuMapper.updateByPrimaryKeySelective(spu);
            // 删除之前的sku
            Sku sku = new Sku();
            sku.setSpuId(spu.getId());
            skuMapper.delete(sku);
        }
        // 添加sku
        Date date = new Date();
        // 查询三级分类
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        // 查询品牌信息
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        List<Sku> skuList = goods.getSkuList();
        for (Sku sku : skuList) {
            sku.setSpuId(spu.getId());
            StringBuilder name = new StringBuilder(spu.getName());
            // 防止空指针异常
            if (StringUtils.isEmpty(sku.getSpec())) {
                sku.setSpec("{}");
            }
            Map<String, String> map = JSON.parseObject(sku.getSpec(), Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                name.append(" ");
                name.append(entry.getValue());
            }
            sku.setName(name.toString());
            sku.setCreateTime(date);
            sku.setUpdateTime(date);
            // 分类id 和 分类名称
            sku.setCategoryId(category.getId());
            sku.setCategoryName(category.getName());
            // 品牌名称
            sku.setBrandName(brand.getName());
            skuMapper.insertSelective(sku);
        }
    }

    /**
     * 查询Spu全部数据
     * @return 查询结果
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    /**
     * 根据ID查询Spu
     * @param id Spu id
     * @return 查询结果
     */
    @Override
    public Spu findById(Integer id) {
        return spuMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Spu
     * @param spu Spu实体类
     */
    @Override
    public void add(Spu spu) {
        spuMapper.insertSelective(spu);
    }

    /**
     * 修改Spu
     * @param spu Spu实体类
     */
    @Override
    public void update(Spu spu) {
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 删除
     * @param id Spu id
     */
    @Override
    public void delete(Integer id) {
        spuMapper.deleteByPrimaryKey(id);
    }

    /**
     * Spu构建查询对象
     * @param spu 查询条件
     * @return 条件构造器
     */
    public Example createExample(Spu spu) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (spu != null) {
            // 
            if (!StringUtils.isEmpty(spu.getId())) {
                criteria.andEqualTo("id", spu.getId());
            }
            // 货号
            if (!StringUtils.isEmpty(spu.getSn())) {
                criteria.andEqualTo("sn", spu.getSn());
            }
            // 商品名称
            if (!StringUtils.isEmpty(spu.getName())) {
                criteria.andLike("name", "%" + spu.getName() + "%");
            }
            // 品牌id
            if (!StringUtils.isEmpty(spu.getBrandId())) {
                criteria.andEqualTo("brandId", spu.getBrandId());
            }
            // 一级分类
            if (!StringUtils.isEmpty(spu.getCategory1Id())) {
                criteria.andEqualTo("category1Id", spu.getCategory1Id());
            }
            // 二级分类
            if (!StringUtils.isEmpty(spu.getCategory2Id())) {
                criteria.andEqualTo("category2Id", spu.getCategory2Id());
            }
            // 三级分类
            if (!StringUtils.isEmpty(spu.getCategory3Id())) {
                criteria.andEqualTo("category3Id", spu.getCategory3Id());
            }
            // 模板id
            if (!StringUtils.isEmpty(spu.getTemplateId())) {
                criteria.andEqualTo("templateId", spu.getTemplateId());
            }
            // 运费模板id
            if (!StringUtils.isEmpty(spu.getFreightId())) {
                criteria.andEqualTo("freightId", spu.getFreightId());
            }
            // 商品图片
            if (!StringUtils.isEmpty(spu.getImage())) {
                criteria.andEqualTo("image", spu.getImage());
            }
            // 图片列表
            if (!StringUtils.isEmpty(spu.getImages())) {
                criteria.andEqualTo("images", spu.getImages());
            }
            // 售后服务
            if (!StringUtils.isEmpty(spu.getSaleService())) {
                criteria.andEqualTo("saleService", spu.getSaleService());
            }
            // 介绍
            if (!StringUtils.isEmpty(spu.getIntroduction())) {
                criteria.andEqualTo("introduction", spu.getIntroduction());
            }
            // 规格列表
            if (!StringUtils.isEmpty(spu.getSpecItems())) {
                criteria.andEqualTo("specItems", spu.getSpecItems());
            }
            // 参数列表
            if (!StringUtils.isEmpty(spu.getParaItems())) {
                criteria.andEqualTo("paraItems", spu.getParaItems());
            }
            // 销量
            if (!StringUtils.isEmpty(spu.getSaleNum())) {
                criteria.andEqualTo("saleNum", spu.getSaleNum());
            }
            // 评论数
            if (!StringUtils.isEmpty(spu.getCommentNum())) {
                criteria.andEqualTo("commentNum", spu.getCommentNum());
            }
            // 是否上架
            if (!StringUtils.isEmpty(spu.getIsMarketable())) {
                criteria.andEqualTo("isMarketable", spu.getIsMarketable());
            }
            // 使用启用规格
            if (!StringUtils.isEmpty(spu.getIsEnableSpec())) {
                criteria.andEqualTo("isEnableSpec", spu.getIsEnableSpec());
            }
            // 是否删除
            if (!StringUtils.isEmpty(spu.getIsDelete())) {
                criteria.andEqualTo("isDelete", spu.getIsDelete());
            }
            // 审核状态
            if (!StringUtils.isEmpty(spu.getStatus())) {
                criteria.andEqualTo("status", spu.getStatus());
            }
        }
        return example;
    }

    /**
     * Spu条件查询
     * @param spu 查询条件
     * @return 查询结果
     */
    @Override
    public List<Spu> findList(Spu spu) {
        // 构建查询条件
        Example example = createExample(spu);
        // 根据构建的条件查询数据
        return spuMapper.selectByExample(example);
    }

    /**
     * Spu分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Spu> findPage(Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<>(spuMapper.selectAll());
    }

    /**
     * Spu条件 + 分页查询
     * @param spu 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Spu> findPage(Spu spu, Integer page, Integer size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(spu);
        // 执行搜索
        return new PageInfo<>(spuMapper.selectByExample(example));
    }

}
