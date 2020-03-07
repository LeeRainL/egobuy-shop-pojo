package com.egobuy.egobuysearchservice.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * @author yadonghe
 * @date 2020-03-07 15:06
 */
public interface IndexService {
    void importIndex() throws IOException, SolrServerException;
}
