package com.changgou.order.service.impl;

import com.changgou.order.dao.OrderItemMapper;
import com.changgou.order.pojo.OrderItem;
import com.changgou.order.service.OrderItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo OrderItem业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    /**
     * OrderItem持久层接口
     */
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 查询OrderItem全部数据
     * @return 查询结果
     */
    @Override
    public List<OrderItem> findAll() {
        return orderItemMapper.selectAll();
    }

    /**
     * 根据ID查询OrderItem
     * @param id OrderItem id
     * @return 查询结果
     */
    @Override
    public OrderItem findById(Integer id){
        return orderItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加OrderItem
     * @param orderItem OrderItem实体类
     */
    @Override
    public void add(OrderItem orderItem){
        orderItemMapper.insertSelective(orderItem);
    }

    /**
     * 修改OrderItem
     * @param orderItem OrderItem实体类
     */
    @Override
    public void update(OrderItem orderItem){
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    /**
     * 删除
     * @param id OrderItem id
     */
    @Override
    public void delete(Integer id){
        orderItemMapper.deleteByPrimaryKey(id);
    }

    /**
     * OrderItem构建查询对象
     * @param orderItem 查询条件
     * @return 条件构造器
     */
    public Example createExample(OrderItem orderItem){
        Example example=new Example(OrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if(orderItem!=null){
            // ID
            if(!StringUtils.isEmpty(orderItem.getId())){
                    criteria.andEqualTo("id",orderItem.getId());
            }
            // 1级分类
            if(!StringUtils.isEmpty(orderItem.getCategoryId1())){
                    criteria.andEqualTo("categoryId1",orderItem.getCategoryId1());
            }
            // 2级分类
            if(!StringUtils.isEmpty(orderItem.getCategoryId2())){
                    criteria.andEqualTo("categoryId2",orderItem.getCategoryId2());
            }
            // 3级分类
            if(!StringUtils.isEmpty(orderItem.getCategoryId3())){
                    criteria.andEqualTo("categoryId3",orderItem.getCategoryId3());
            }
            // SPU_ID
            if(!StringUtils.isEmpty(orderItem.getSupId())){
                    criteria.andEqualTo("supId",orderItem.getSupId());
            }
            // SKU_ID
            if(!StringUtils.isEmpty(orderItem.getSkuId())){
                    criteria.andEqualTo("skuId",orderItem.getSkuId());
            }
            // 订单ID
            if(!StringUtils.isEmpty(orderItem.getOrderId())){
                    criteria.andEqualTo("orderId",orderItem.getOrderId());
            }
            // 商品名称
            if(!StringUtils.isEmpty(orderItem.getName())){
                    criteria.andLike("name","%"+orderItem.getName()+"%");
            }
            // 单价
            if(!StringUtils.isEmpty(orderItem.getPrice())){
                    criteria.andEqualTo("price",orderItem.getPrice());
            }
            // 数量
            if(!StringUtils.isEmpty(orderItem.getNum())){
                    criteria.andEqualTo("num",orderItem.getNum());
            }
            // 总金额
            if(!StringUtils.isEmpty(orderItem.getMoney())){
                    criteria.andEqualTo("money",orderItem.getMoney());
            }
            // 实付金额
            if(!StringUtils.isEmpty(orderItem.getPayMoney())){
                    criteria.andEqualTo("payMoney",orderItem.getPayMoney());
            }
            // 图片地址
            if(!StringUtils.isEmpty(orderItem.getImage())){
                    criteria.andEqualTo("image",orderItem.getImage());
            }
            // 重量
            if(!StringUtils.isEmpty(orderItem.getWeight())){
                    criteria.andEqualTo("weight",orderItem.getWeight());
            }
            // 运费
            if(!StringUtils.isEmpty(orderItem.getPostFee())){
                    criteria.andEqualTo("postFee",orderItem.getPostFee());
            }
            // 是否退后
            if(!StringUtils.isEmpty(orderItem.getIsReturn())){
                    criteria.andEqualTo("isReturn",orderItem.getIsReturn());
            }
        }
        return example;
    }

    /**
     * OrderItem条件查询
     * @param orderItem 查询条件
     * @return 查询结果
     */
    @Override
    public List<OrderItem> findList(OrderItem orderItem){
        // 构建查询条件
        Example example = createExample(orderItem);
        // 根据构建的条件查询数据
        return orderItemMapper.selectByExample(example);
    }

    /**
     * OrderItem分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<OrderItem> findPage(Integer page, Integer size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<>(orderItemMapper.selectAll());
    }

    /**
     * OrderItem条件 + 分页查询
     * @param orderItem 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<OrderItem> findPage(OrderItem orderItem, Integer page, Integer size){
        // 分页
        PageHelper.startPage(page,size);
        // 搜索条件构建
        Example example = createExample(orderItem);
        // 执行搜索
        return new PageInfo<>(orderItemMapper.selectByExample(example));
    }

}
