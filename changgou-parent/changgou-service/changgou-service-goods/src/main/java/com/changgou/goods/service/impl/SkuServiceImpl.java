package com.changgou.goods.service.impl;

import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.service.SkuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * Demo Sku业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class SkuServiceImpl implements SkuService {

    /**
     * Sku持久层接口
     */
    @Autowired
    private SkuMapper skuMapper;

    /**
     * 查询Sku全部数据
     * @return 查询结果
     */
    @Override
    public List<Sku> findAll() {
        return skuMapper.selectAll();
    }

    /**
     * 根据ID查询Sku
     * @param id Sku id
     * @return 查询结果
     */
    @Override
    public Sku findById(Integer id){
        return  skuMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Sku
     * @param sku Sku实体类
     */
    @Override
    public void add(Sku sku){
        skuMapper.insertSelective(sku);
    }

    /**
     * 修改Sku
     * @param sku Sku实体类
     */
    @Override
    public void update(Sku sku){
        skuMapper.updateByPrimaryKeySelective(sku);
    }

    /**
     * 删除
     * @param id Sku id
     */
    @Override
    public void delete(Integer id){
        skuMapper.deleteByPrimaryKey(id);
    }

    /**
     * Sku构建查询对象
     * @param sku 查询条件
     * @return 条件构造器
     */
    public Example createExample(Sku sku){
        Example example=new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        if(sku!=null){
            // 商品id
            if(!StringUtils.isEmpty(sku.getId())){
                    criteria.andEqualTo("id",sku.getId());
            }
            // 商品条码
            if(!StringUtils.isEmpty(sku.getSn())){
                    criteria.andEqualTo("sn",sku.getSn());
            }
            // 商品名称
            if(!StringUtils.isEmpty(sku.getName())){
                    criteria.andLike("name","%"+sku.getName()+"%");
            }
            // 价格
            if(!StringUtils.isEmpty(sku.getPrice())){
                    criteria.andEqualTo("price",sku.getPrice());
            }
            // 库存数量
            if(!StringUtils.isEmpty(sku.getNum())){
                    criteria.andEqualTo("num",sku.getNum());
            }
            // 库存预警数量
            if(!StringUtils.isEmpty(sku.getAlertNum())){
                    criteria.andEqualTo("alertNum",sku.getAlertNum());
            }
            // 商品图片
            if(!StringUtils.isEmpty(sku.getImage())){
                    criteria.andEqualTo("image",sku.getImage());
            }
            // 商品图片列表
            if(!StringUtils.isEmpty(sku.getImages())){
                    criteria.andEqualTo("images",sku.getImages());
            }
            // 重量(克)
            if(!StringUtils.isEmpty(sku.getWeight())){
                    criteria.andEqualTo("weight",sku.getWeight());
            }
            // 创建时间
            if(!StringUtils.isEmpty(sku.getCreateTime())){
                    criteria.andEqualTo("createTime",sku.getCreateTime());
            }
            // 更新时间
            if(!StringUtils.isEmpty(sku.getUpdateTime())){
                    criteria.andEqualTo("updateTime",sku.getUpdateTime());
            }
            // SPU ID
            if(!StringUtils.isEmpty(sku.getSpuId())){
                    criteria.andEqualTo("spuId",sku.getSpuId());
            }
            // 类目id
            if(!StringUtils.isEmpty(sku.getCategoryId())){
                    criteria.andEqualTo("categoryId",sku.getCategoryId());
            }
            // 类目名称
            if(!StringUtils.isEmpty(sku.getCategoryName())){
                    criteria.andEqualTo("categoryName",sku.getCategoryName());
            }
            // 品牌名称
            if(!StringUtils.isEmpty(sku.getBrandName())){
                    criteria.andEqualTo("brandName",sku.getBrandName());
            }
            // 规格
            if(!StringUtils.isEmpty(sku.getSpec())){
                    criteria.andEqualTo("spec",sku.getSpec());
            }
            // 销量
            if(!StringUtils.isEmpty(sku.getSaleNum())){
                    criteria.andEqualTo("saleNum",sku.getSaleNum());
            }
            // 评论数
            if(!StringUtils.isEmpty(sku.getCommentNum())){
                    criteria.andEqualTo("commentNum",sku.getCommentNum());
            }
            // 商品状态,1正常,2下架,3删除
            if(!StringUtils.isEmpty(sku.getStatus())){
                    criteria.andEqualTo("status",sku.getStatus());
            }
        }
        return example;
    }

    /**
     * Sku条件查询
     * @param sku 查询条件
     * @return 查询结果
     */
    @Override
    public List<Sku> findList(Sku sku){
        // 构建查询条件
        Example example = createExample(sku);
        // 根据构建的条件查询数据
        return skuMapper.selectByExample(example);
    }

    /**
     * Sku分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Sku> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(skuMapper.selectAll());
    }

    /**
     * Sku条件 + 分页查询
     * @param sku 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Sku> findPage(Sku sku, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(sku);
        // 执行搜索
        return new PageInfo<>(skuMapper.selectByExample(example));
    }

    /**
     * 商品库存递减
     * @param decrMap 商品信息
     */
    @Override
    public void decrCount(Map<String, Integer> decrMap) {
        for (Map.Entry<String, Integer> entry : decrMap.entrySet()) {
            Integer id = Integer.valueOf(entry.getKey());
            Integer num = entry.getValue();
            // 采用行级锁控制超卖 update tb_sku set num=num-#{num} where id=#{id} and num>=#{num}
            // 数据库中每条记录都拥有行级锁, 此时只能允许一个事务修改数据, 只有等该事务结束后, 其他事务才能操作该记录
            Integer count = skuMapper.decrCount(id, num);
            if (count <= 0){
                throw new RuntimeException("商品库存不足, 回滚事务");
            }
        }
    }
}
