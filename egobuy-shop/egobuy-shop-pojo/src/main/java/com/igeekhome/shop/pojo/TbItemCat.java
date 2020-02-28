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
public class TbItemCat implements Serializable {

    private static final long serialVersionUID = 1582854885913L;


    /**
    * 主键
    * 类目ID
    * isNullAble:0
    */
    private Long id;

    /**
    * 父类目ID=0时，代表的是一级的类目
    * isNullAble:1
    */
    private Long parentId;

    /**
    * 类目名称
    * isNullAble:1
    */
    private String name;

    /**
    * 状态。可选值:1(正常),2(删除)
    * isNullAble:1,defaultVal:1
    */
    private Integer status;

    /**
    * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
    * isNullAble:1
    */
    private Integer sortOrder;

    /**
    * 该类目是否为父类目，1为true，0为false
    * isNullAble:1,defaultVal:1
    */
    private Integer isParent;

    /**
    * 创建时间
    * isNullAble:1
    */
    private Date created;

    /**
    * 创建时间
    * isNullAble:1
    */
    private Date updated;


}
