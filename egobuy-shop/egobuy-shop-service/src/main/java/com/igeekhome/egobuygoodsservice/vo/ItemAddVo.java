package com.igeekhome.egobuygoodsservice.vo;

import com.igeekhome.shop.pojo.TbItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yadonghe
 * @date 2020-03-03 10:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemAddVo extends TbItem {
    private String content;
}
