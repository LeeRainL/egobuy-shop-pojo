package com.igeekhome.shop.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
*
*  @author author
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "tb_item_aaa")
public class TbItem implements Serializable {

    private static final long serialVersionUID = 1582854857045L;


    /**
    * 主键
    * 商品id，同时也是商品编号
    * isNullAble:0
    */
    @Id
    private Long id;

    /**
    * 商品标题
    * isNullAble:0
    */
    private String title;

    /**
    * 商品卖点
    * isNullAble:1
    */
    private String sellPoint;

    /**
    * 商品价格，单位为：分
    * isNullAble:0
    */
    private Long price;

    /**
    * 库存数量
    * isNullAble:0
    */
    private Integer num;

    /**
    * 商品条形码
    * isNullAble:1
    */
    private String barcode;

    /**
    * 商品图片
    * isNullAble:1
    */
    private String image;

    /**
    * 所属类目，叶子类目
    * isNullAble:0
    */
    private Long cid;

    /**
    * 商品状态，1-正常，2-下架，3-删除
    * isNullAble:0,defaultVal:1
    */
    private Integer status;

    /**
    * 创建时间
    * isNullAble:0
    */
    private Date created;

    /**
    * 更新时间
    * isNullAble:0
    */
    private Date updated;


    /**
     * 商品对应分类对象
     */
    @Transient
    private TbItemCat itemCat;


}
