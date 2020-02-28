package com.igeekhome.egobuygoodsservice.mapper;

import com.igeekhome.egobuygoodsservice.vo.ItemQueryVo;
import com.igeekhome.shop.pojo.TbItem;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-02-28 10:08
 */
public interface ItemMapper extends Mapper<TbItem> {

    List<TbItem> selectByQuery(ItemQueryVo queryVo);
}
