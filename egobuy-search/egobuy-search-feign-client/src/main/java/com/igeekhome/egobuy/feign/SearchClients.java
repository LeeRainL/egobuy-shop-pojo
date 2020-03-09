package com.igeekhome.egobuy.feign;

import com.egobuy.search.pojo.SearchItem;
import com.egobuy.search.pojo.SearchResponseEntity;
import com.igeekhome.egobuy.util.ResponseEntityV2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-03-05 10:55
 */
//声明当前接口是一个Feign接口，并且指定需要调用的服务名称
@FeignClient("EGOBUY-SEARCH-SERVICE")
public interface SearchClients {
    @RequestMapping("/index/select") ///index/select?keyword=&page=&limit=
    SearchResponseEntity search(@RequestParam("keyword") String keyword, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);
}
