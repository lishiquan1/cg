package com.changgou.user.dao;

import com.changgou.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * Demo User持久层接口
 *
 * @author lishiquan
 */
public interface UserMapper extends Mapper<User> {
    /**
     * 增加用户积分
     * @param id 用户id
     * @param points 积分
     */
    @Update("update tb_sku set points=points+#{points} where id=#{id}")
    void addPoints(@Param("id") Integer id,@Param("points") Integer points);
}
