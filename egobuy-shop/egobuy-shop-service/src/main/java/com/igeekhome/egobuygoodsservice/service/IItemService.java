package com.igeekhome.egobuygoodsservice.service;

import com.igeekhome.egobuy.util.CustomPageInfo;
import com.igeekhome.egobuygoodsservice.vo.ItemAddVo;
import com.igeekhome.egobuygoodsservice.vo.ItemQueryVo;
import com.igeekhome.shop.pojo.TbItem;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-02-27 15:16
 */
public interface IItemService {

    CustomPageInfo<TbItem> select(Integer page, Integer limit, ItemQueryVo query);

    void update(TbItem tbItem);

    void addItemAndDesc(ItemAddVo item);

    List<TbItem> select();

    /**
     * 根据id查询商品详情并生成静态HTML页面
     * @param id
     */
    void outPutHTML(Long id);

    TbItem get(Long id);
}
