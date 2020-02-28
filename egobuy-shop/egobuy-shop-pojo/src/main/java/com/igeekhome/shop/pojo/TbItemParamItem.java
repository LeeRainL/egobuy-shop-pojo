package com.igeekhome.shop.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

/**
*
*  @author author
*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TbItemParamItem implements Serializable {

    private static final long serialVersionUID = 1582854897950L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    private Long id;

    /**
    * 商品ID
    * isNullAble:1
    */
    private Long itemId;

    /**
    * 参数数据，格式为json格式
    * isNullAble:1
    */
    private String paramData;

    /**
    * 
    * isNullAble:1
    */
    private Date created;

    /**
    * 
    * isNullAble:1
    */
    private Date updated;


}
