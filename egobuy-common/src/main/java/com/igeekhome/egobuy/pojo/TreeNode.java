package com.igeekhome.egobuy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tree结点数据
 * @author yadonghe
 * @date 2020-03-02 14:19
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TreeNode {
    /**
     * 当前节点id
     */
    private Long id;
    /**
     * 当前节点名称
     */
    private String label;
    /**
     * 当前节点是否为叶子节点
     */
    private Boolean isLeaf;
}
