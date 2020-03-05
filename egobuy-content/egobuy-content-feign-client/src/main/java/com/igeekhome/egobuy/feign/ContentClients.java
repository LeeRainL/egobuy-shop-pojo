package com.igeekhome.egobuy.feign;

import com.igeekhome.egobuy.content.pojo.TbContent;
import com.igeekhome.egobuy.util.ResponseEntity;
import com.igeekhome.egobuy.util.ResponseEntityV2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yadonghe
 * @date 2020-03-05 10:55
 */
//声明当前接口是一个Feign接口，并且指定需要调用的服务名称
@FeignClient("EGOBUY-CONTENT-SERVICE")
public interface ContentClients {
    @RequestMapping("/content/list/{id}")
    ResponseEntityV2<TbContent> listContents(@PathVariable(name = "id") Long id);
}
