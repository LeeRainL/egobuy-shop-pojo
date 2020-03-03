package com.igeekhome.egobuy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Tree结点数据
 * @author yadonghe
 * @date 2020-03-02 14:19
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TreeNodeV2 {
    /**
     * 当前节点id
     */
    private Long id;
    /**
     * 当前节点的父节点id
     */
    private Long parentId;
    /**
     * 当前节点名称
     */
    private String label;
    /**
     * 当前节点是否为叶子节点
     */
    private Boolean isLeaf;
    /**
     * 当前节点的子节点
     */
    private List<TreeNodeV2> children;
}
