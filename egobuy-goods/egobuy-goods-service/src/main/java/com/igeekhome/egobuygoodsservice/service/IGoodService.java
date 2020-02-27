package com.igeekhome.egobuygoodsservice.service;

import com.igeekhome.goods.pojo.Good;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-02-27 15:16
 */
public interface IGoodService {
    List<Good> list();

    int add(Good good);
}
