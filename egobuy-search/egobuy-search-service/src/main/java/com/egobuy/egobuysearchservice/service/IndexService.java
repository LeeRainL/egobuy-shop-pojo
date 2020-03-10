package com.egobuy.egobuysearchservice.service;

import com.egobuy.search.pojo.SearchItem;
import com.egobuy.search.pojo.SearchResponseEntity;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

/**
 * @author yadonghe
 * @date 2020-03-07 15:06
 */
public interface IndexService {
    void importIndex() throws IOException, SolrServerException;

    /**
     * 查询索引库
     * @param keyword
     * @return
     */
    SearchResponseEntity select(String keyword, Integer page, Integer limit);

    void addIndex(Long id);
}
