package com.egobuy.egobuysearchservice.controller;

import com.egobuy.egobuysearchservice.service.IndexService;
import com.igeekhome.egobuy.exception.CustomException;
import com.igeekhome.egobuy.exception.CustomExceptionType;
import com.igeekhome.egobuy.util.ResponseEntity;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author yadonghe
 * @date 2020-03-07 15:04
 */
@RestController
@RequestMapping("/index")
public class ImportIndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/import")
    public ResponseEntity importIndex() {
        try {
            indexService.importIndex();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(CustomExceptionType.SERVER_ERROR, "索引库导入失败");
        }
        return ResponseEntity.success();
    }
}
