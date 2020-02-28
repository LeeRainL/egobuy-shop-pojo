package com.igeekhome.egobuygoodsservice.vo;

import com.igeekhome.shop.pojo.TbItem;

import java.util.Date;

/**
 * @author yadonghe
 * @date 2020-02-28 10:20
 */

public class ItemQueryVo extends TbItem {
    private Long minPrice;
    private Long maxPrice;

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Long maxPrice) {
        this.maxPrice = maxPrice;
    }
}
