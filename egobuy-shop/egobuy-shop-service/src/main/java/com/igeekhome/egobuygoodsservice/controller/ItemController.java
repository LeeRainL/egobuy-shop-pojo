package com.igeekhome.egobuygoodsservice.controller;

import com.igeekhome.egobuy.exception.CustomException;
import com.igeekhome.egobuy.exception.CustomExceptionType;
import com.igeekhome.egobuy.util.ResponseEntity;
import com.igeekhome.egobuy.util.ResponseEntityV2;
import com.igeekhome.egobuygoodsservice.service.IItemService;
import com.igeekhome.egobuygoodsservice.vo.ItemAddVo;
import com.igeekhome.egobuygoodsservice.vo.ItemQueryVo;
import com.igeekhome.shop.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-02-27 15:17
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private IItemService itemService;

    /**
     * @param page 当前页
     * @param limit 每页查询条数
     * @param query 查询条件
     * @return
     */
    @RequestMapping("/list")
    public ResponseEntity list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit, ItemQueryVo query) {

        return ResponseEntity.success(itemService.select(page, limit, query));
    }

    @RequestMapping("/all")
    public List<TbItem> all() {
        //return ResponseEntityV2.success(itemService.select());
        return itemService.select();
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody TbItem tbItem) {
        if (StringUtils.isEmpty(tbItem.getId())) {
            throw new CustomException(CustomExceptionType.USER_ERROR, "商品id不能为空");
        }
        itemService.update(tbItem);
        return ResponseEntity.success();
    }

    @RequestMapping("/add")
    public ResponseEntity add(@RequestBody ItemAddVo item) {
        itemService.addItemAndDesc(item);
        return ResponseEntity.success();
    }

    @RequestMapping("/get/{id}")
    public TbItem get(@PathVariable("id") Long id) {
        return itemService.get(id);
    }


}
