package com.igeekhome.goods.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author yadonghe
 * @date 2020-02-27 14:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="t_goods")
public class Good {
    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //修饰非主键字段，默认可以省略
    //如表字段名与类的属性名不一致时，不能省略
    //@Column(name = "user_name")
    @Column(name = "`name`")
    private String name;
    @Column
    private String pic;
    @Column(name = "`desc`")
    private String desc;
    @Column
    private BigDecimal price;

}
