package com.igeekhome.egobuygoodsservice.controller;

import com.igeekhome.egobuy.pojo.TreeNode;
import com.igeekhome.egobuy.util.ResponseEntity;
import com.igeekhome.egobuygoodsservice.service.IItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类Controller
 * @author yadonghe
 * @date 2020-03-02 14:21
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private IItemCatService itemCatService;
    /**
     * 根据父节点id查找对应的所有字节点
     * @param parentId 父节点id，默认值为0
     * @return
     */
    @RequestMapping("/list")
    public ResponseEntity list(@RequestParam(defaultValue = "0",name = "id") Long parentId) {
        List<TreeNode> treeNodes = itemCatService.selectNodes(parentId);
        return ResponseEntity.success(treeNodes);
    }

}
