package com.igeekhome.egobuygoodsservice.controller;

import com.igeekhome.egobuygoodsservice.service.IGoodService;
import com.igeekhome.goods.pojo.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-02-27 15:17
 */
@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private IGoodService goodService;

    @RequestMapping("/list")
    public List<Good> list() {
        return goodService.list();
    }

    @RequestMapping("/add")
    public long add(Good good) {
        goodService.add(good);
        return good.getId();
    }
}
