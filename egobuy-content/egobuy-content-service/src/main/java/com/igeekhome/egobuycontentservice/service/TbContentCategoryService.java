package com.igeekhome.egobuycontentservice.service;

import com.igeekhome.egobuy.content.pojo.TbContentCategory;
import com.igeekhome.egobuy.pojo.TreeNodeV2;
import java.util.List;

/**
 * 内容分类(TbContentCategory)表服务接口
 *
 * @author makejava
 * @since 2020-03-03 11:33:49
 */
public interface TbContentCategoryService {


    List<TreeNodeV2> queryTreeNode();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbContentCategory queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbContentCategory> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbContentCategory 实例对象
     * @return 实例对象
     */
    TbContentCategory insert(TbContentCategory tbContentCategory);

    /**
     * 修改数据
     *
     * @param tbContentCategory 实例对象
     * @return 实例对象
     */
    TbContentCategory update(TbContentCategory tbContentCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}