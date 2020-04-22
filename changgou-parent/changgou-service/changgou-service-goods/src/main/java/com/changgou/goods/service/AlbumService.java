package com.changgou.goods.service;

import com.changgou.goods.pojo.Album;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Demo Album业务层接口
 *
 * @author lishiquan
 */
public interface AlbumService {
    
    /**
     * 查询所有Album
     * @return 查询结果
     */
    List<Album> findAll();

    /**
     * 根据ID查询Album
     * @param id Album id
     * @return 查询结果
     */
    Album findById(Integer id);


    /**
     * 新增Album
     * @param album Album实体类
     */
    void add(Album album);


    /**
     * 修改Album数据
     * @param album Album实体类
     */
    void update(Album album);

    /**
     * 删除Album
     * @param id Album id
     */
    void delete(Integer id);


    /**
     * Album多条件搜索方法
     * @param album 查询条件
     * @return 查询结果
     */
    List<Album> findList(Album album);

    /**
     * Album分页查询
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Album> findPage(Integer page, Integer size);

    /**
     * Album条件查询 + 分页
     * @param album 分页条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageInfo<Album> findPage(Album album, Integer page, Integer size);

}
