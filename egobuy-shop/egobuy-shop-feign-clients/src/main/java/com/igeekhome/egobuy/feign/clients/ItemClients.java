package com.igeekhome.egobuy.feign.clients;

import com.igeekhome.shop.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-03-07 15:12
 */
@FeignClient("EGOBUY-SHOP-SERVICE")
public interface ItemClients {
    @RequestMapping("/item/all")
    List<TbItem> selectAll();
    @RequestMapping("/item/get/{id}")
    TbItem select(@PathVariable("id") Long id);
}
