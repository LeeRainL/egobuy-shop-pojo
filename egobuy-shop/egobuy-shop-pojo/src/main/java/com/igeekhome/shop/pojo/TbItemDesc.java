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
public class TbItemDesc implements Serializable {

    private static final long serialVersionUID = 1582854889038L;


    /**
    * 主键
    * 商品ID
    * isNullAble:0
    */
    private Long itemId;

    /**
    * 商品描述
    * isNullAble:1
    */
    private String itemDesc;

    /**
    * 创建时间
    * isNullAble:1
    */
    private Date created;

    /**
    * 更新时间
    * isNullAble:1
    */
    private Date updated;




}
