package com.changgou.seckill.timer;

import com.changgou.common.util.DateUtil;
import com.changgou.seckill.dao.SeckillGoodsMapper;
import com.changgou.seckill.pojo.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Demo 定时将秒杀商品存入到Redis缓存
 *
 * @author lishiquan
 * @date 2020/4/27 9:41 上午
 */
@Component
public class SeckillGoodsPushTask {
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 定时操作
     * 每60秒执行一次
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void loadGoodsPushRedis() {
        // 求时间菜单
        List<Date> dateMenus = DateUtil.getDateMenus();
        // 循环查询每个时间区间的秒杀商品
        for (Date dateMenu : dateMenus) {
            // 时间的字符串格式
            String timeSpace = "SeckillGoods_" + DateUtil.dateToStr(dateMenu, "yyyyMMddHH");
            // 条件查询
            Example example = new Example(SeckillGoods.class);
            Example.Criteria criteria = example.createCriteria();
            // 审核通过
            criteria.andEqualTo("status", "1");
            // 商品库存
            criteria.andGreaterThan("stockCount", "0");
            // 时间段
            criteria.andGreaterThanOrEqualTo("staterTime", dateMenu);
            criteria.andLessThan("endTime", DateUtil.addDateHour(dateMenu, 2));
            // 排除已经存入到Redis中的SeckillGoods
            Set keys = redisTemplate.boundHashOps(timeSpace).keys();
            if (keys != null && keys.size() > 0) {
                // 排除
                criteria.andNotIn("id", keys);
            }
            // 查询数据
            List<SeckillGoods> seckillGoodsList = seckillGoodsMapper.selectByExample(example);
            for (SeckillGoods seckillGoods : seckillGoodsList) {
                // 存入到Redis
                redisTemplate.boundHashOps(timeSpace).put(seckillGoods.getId(), seckillGoods);
                // 给每个商品做个队列
                // Integer[] ids = this.putAllIds(seckillGoods.getId(), seckillGoods.getStockCount());
                // redisTemplate.boundListOps("SeckillGoodsCountList_" + seckillGoods.getId()).leftPushAll(ids);
                redisTemplate.boundHashOps("SeckillGoodsCountList").increment(seckillGoods.getId(), seckillGoods.getStockCount());
            }
        }
    }

    /**
     * 获取每个商品的ID集合
     * @param id 商品id
     * @param num 商品数量
     * @return 队列
     */
    public Integer[] putAllIds(Integer id, Integer num) {
        Integer[] ids = new Integer[num];
        for (int i = 0; i < num; i++) {
            ids[i] = id;
        }
        return ids;
    }

}
