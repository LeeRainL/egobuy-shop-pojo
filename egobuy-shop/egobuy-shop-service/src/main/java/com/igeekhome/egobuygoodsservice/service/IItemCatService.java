package com.igeekhome.egobuygoodsservice.service;

import com.igeekhome.egobuy.pojo.TreeNode;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-03-02 14:24
 */
public interface IItemCatService {
    /**
     * 根据父节点id，查找对应的子节点tree数据
     * @param parentId
     * @return
     */
    List<TreeNode> selectNodes(Long parentId);
}
