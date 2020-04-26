package com.changgou.user.service.impl;

import com.changgou.user.dao.UserMapper;
import com.changgou.user.pojo.User;
import com.changgou.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo User业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * User持久层接口
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录验证
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public User login(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> users = userMapper.select(user);
        if (users.size() == 1){
            return users.get(0);
        }
        return null;
    }

    /**
     * 查询User全部数据
     * @return 查询结果
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    /**
     * 根据ID查询User
     * @param id User id
     * @return 查询结果
     */
    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加User
     * @param user User实体类
     */
    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }

    /**
     * 修改User
     * @param user User实体类
     */
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除
     * @param id User id
     */
    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * User构建查询对象
     * @param user 查询条件
     * @return 条件构造器
     */
    public Example createExample(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (user != null) {
            // 用户id
            if (!StringUtils.isEmpty(user.getId())) {
                criteria.andEqualTo("id", user.getId());
            }
            // 用户名
            if (!StringUtils.isEmpty(user.getUsername())) {
                criteria.andLike("username", "%" + user.getUsername() + "%");
            }
            // 密码, 加密存储
            if (!StringUtils.isEmpty(user.getPassword())) {
                criteria.andEqualTo("password", user.getPassword());
            }
            // 注册手机号
            if (!StringUtils.isEmpty(user.getPhone())) {
                criteria.andEqualTo("phone", user.getPhone());
            }
            // 注册邮箱
            if (!StringUtils.isEmpty(user.getEmail())) {
                criteria.andEqualTo("email", user.getEmail());
            }
            // 创建时间
            if (!StringUtils.isEmpty(user.getCreateTime())) {
                criteria.andEqualTo("createTime", user.getCreateTime());
            }
            // 修改时间
            if (!StringUtils.isEmpty(user.getUpdateTime())) {
                criteria.andEqualTo("updateTime", user.getUpdateTime());
            }
            // 会员来源: 1 PC, 2 H5, 3 Android, 4 IOS
            if (!StringUtils.isEmpty(user.getSourceType())) {
                criteria.andEqualTo("sourceType", user.getSourceType());
            }
            // 昵称
            if (!StringUtils.isEmpty(user.getNickName())) {
                criteria.andEqualTo("nickName", user.getNickName());
            }
            // 真实姓名
            if (!StringUtils.isEmpty(user.getName())) {
                criteria.andLike("name", "%" + user.getName() + "%");
            }
            // 使用状态: 1 正常, 0 非正常
            if (!StringUtils.isEmpty(user.getStatus())) {
                criteria.andEqualTo("status", user.getStatus());
            }
            // 头像地址
            if (!StringUtils.isEmpty(user.getHeadPic())) {
                criteria.andEqualTo("headPic", user.getHeadPic());
            }
            // QQ号码
            if (!StringUtils.isEmpty(user.getQq())) {
                criteria.andEqualTo("qq", user.getQq());
            }
            // 手机是否验证: 0 否, 1 是
            if (!StringUtils.isEmpty(user.getIsMobileCheck())) {
                criteria.andEqualTo("isMobileCheck", user.getIsMobileCheck());
            }
            // 邮箱是否验证: 0 否, 1 是
            if (!StringUtils.isEmpty(user.getIsEmailCheck())) {
                criteria.andEqualTo("isEmailCheck", user.getIsEmailCheck());
            }
            // 性别: 1 男, 0 女
            if (!StringUtils.isEmpty(user.getSex())) {
                criteria.andEqualTo("sex", user.getSex());
            }
            // 会员等级
            if (!StringUtils.isEmpty(user.getUserLevel())) {
                criteria.andEqualTo("userLevel", user.getUserLevel());
            }
            // 积分
            if (!StringUtils.isEmpty(user.getPoints())) {
                criteria.andEqualTo("points", user.getPoints());
            }
            // 生日
            if (!StringUtils.isEmpty(user.getBirthday())) {
                criteria.andEqualTo("birthday", user.getBirthday());
            }
            // 上次登录时间
            if (!StringUtils.isEmpty(user.getLastLoginTime())) {
                criteria.andEqualTo("lastLoginTime", user.getLastLoginTime());
            }
        }
        return example;
    }

    /**
     * User条件查询
     * @param user 查询条件
     * @return 查询结果
     */
    @Override
    public List<User> findList(User user) {
        // 构建查询条件
        Example example = createExample(user);
        // 根据构建的条件查询数据
        return userMapper.selectByExample(example);
    }

    /**
     * User分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<User> findPage(Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<>(userMapper.selectAll());
    }

    /**
     * User条件 + 分页查询
     * @param user 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<User> findPage(User user, Integer page, Integer size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(user);
        // 执行搜索
        return new PageInfo<>(userMapper.selectByExample(example));
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public User findByName(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> users = userMapper.select(user);
        if (users.size() == 1){
            return users.get(0);
        }
        return null;
    }

    /**
     * 增加用户积分
     * @param id 用户id
     * @param points 积分
     */
    @Override
    public void addPoints(Integer id, Integer points) {
        userMapper.addPoints(id,points);
    }

}
