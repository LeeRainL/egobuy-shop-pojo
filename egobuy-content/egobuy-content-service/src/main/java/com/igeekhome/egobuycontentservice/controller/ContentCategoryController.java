package com.igeekhome.egobuycontentservice.controller;

import com.igeekhome.egobuy.content.pojo.TbContentCategory;
import com.igeekhome.egobuy.pojo.TreeNodeV2;
import com.igeekhome.egobuy.util.ResponseEntity;
import com.igeekhome.egobuycontentservice.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-03-03 13:41
 */
@RestController
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private TbContentCategoryService tbContentCategoryService;
    @RequestMapping("/list")
    public ResponseEntity list() {
        List<TreeNodeV2> nodes = tbContentCategoryService.queryTreeNode();
        return ResponseEntity.success(nodes);
    }

    @RequestMapping("/add")
    public ResponseEntity add(TbContentCategory category) {
        TbContentCategory result = tbContentCategoryService.insert(category);
        return ResponseEntity.success(result);
    }
    @RequestMapping("/update")
    public ResponseEntity update(TbContentCategory category) {
        tbContentCategoryService.update(category);
        return ResponseEntity.success();
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        tbContentCategoryService.deleteById(id);
        return ResponseEntity.success();
    }

}
