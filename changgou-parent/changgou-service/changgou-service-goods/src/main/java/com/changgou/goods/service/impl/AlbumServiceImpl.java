package com.changgou.goods.service.impl;

import com.changgou.goods.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Demo Album业务层接口实现类
 *
 * @author lishiquan
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    /**
     * Album持久层接口
     */
    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 查询Album全部数据
     * @return 查询结果
     */
    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    /**
     * 根据ID查询Album
     * @param id Album id
     * @return 查询结果
     */
    @Override
    public Album findById(Integer id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加Album
     * @param album Album实体类
     */
    @Override
    public void add(Album album) {
        albumMapper.insertSelective(album);
    }

    /**
     * 修改Album
     * @param album Album实体类
     */
    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    /**
     * 删除
     * @param id Album id
     */
    @Override
    public void delete(Integer id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    /**
     * Album构建查询对象
     * @param album 查询条件
     * @return 条件构造器
     */
    public Example createExample(Album album) {
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if (album != null) {
            // 
            if (!StringUtils.isEmpty(album.getId())) {
                criteria.andEqualTo("id", album.getId());
            }
            // 相册名称
            if (!StringUtils.isEmpty(album.getTitle())) {
                criteria.andLike("title", "%" + album.getTitle() + "%");
            }
            // 相册封面
            if (!StringUtils.isEmpty(album.getImage())) {
                criteria.andEqualTo("image", album.getImage());
            }
            // 图片列表
            if (!StringUtils.isEmpty(album.getImageItems())) {
                criteria.andEqualTo("imageItems", album.getImageItems());
            }
        }
        return example;
    }

    /**
     * Album条件查询
     * @param album 查询条件
     * @return 查询结果
     */
    @Override
    public List<Album> findList(Album album) {
        // 构建查询条件
        Example example = createExample(album);
        // 根据构建的条件查询数据
        return albumMapper.selectByExample(example);
    }

    /**
     * Album分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Album> findPage(Integer page, Integer size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<>(albumMapper.selectAll());
    }

    /**
     * Album条件 + 分页查询
     * @param album 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Album> findPage(Album album, Integer page, Integer size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索条件构建
        Example example = createExample(album);
        // 执行搜索
        return new PageInfo<>(albumMapper.selectByExample(example));
    }

}
