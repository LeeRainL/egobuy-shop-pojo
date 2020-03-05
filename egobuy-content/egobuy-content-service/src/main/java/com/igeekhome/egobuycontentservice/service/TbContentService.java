package com.igeekhome.egobuycontentservice.service;


import com.igeekhome.egobuy.content.pojo.TbContent;
import com.igeekhome.egobuy.util.CustomPageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * (TbContent)表服务接口
 *
 * @author makejava
 * @since 2020-03-04 11:01:40
 */
public interface TbContentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbContent queryById(Long id);

    /**
     * 通过parentId查询数据
     * @param parentId 需要查询数据的父节点ID
     * @param page 查询起始位置
     * @param limit 查询条数
     *
     */
    CustomPageInfo<TbContent> queryByParentId(Long parentId, Integer page, Integer limit);
    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbContent> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbContent 实例对象
     * @return 实例对象
     */
    TbContent insert(TbContent tbContent);

    /**
     * 修改数据
     *
     * @param tbContent 实例对象
     * @return 实例对象
     */
    TbContent update(TbContent tbContent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}