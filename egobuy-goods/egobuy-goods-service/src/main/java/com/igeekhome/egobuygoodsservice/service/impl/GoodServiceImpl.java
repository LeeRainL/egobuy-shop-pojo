package com.igeekhome.egobuygoodsservice.service.impl;

import com.igeekhome.egobuygoodsservice.mapper.GoodMapper;
import com.igeekhome.egobuygoodsservice.service.IGoodService;
import com.igeekhome.goods.pojo.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-02-27 15:17
 */
@Service
public class GoodServiceImpl implements IGoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Override
    public List<Good> list() {
        return goodMapper.selectAll();
    }

    @Override
    public int add(Good good) {
        return goodMapper.insert(good);
    }


}
