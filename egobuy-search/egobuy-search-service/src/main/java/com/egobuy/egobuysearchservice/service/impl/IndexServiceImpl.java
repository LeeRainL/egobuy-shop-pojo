package com.egobuy.egobuysearchservice.service.impl;

import com.egobuy.egobuysearchservice.pojo.SearchItem;
import com.egobuy.egobuysearchservice.service.IndexService;
import com.igeekhome.egobuy.feign.clients.ItemClients;
import com.igeekhome.shop.pojo.TbItem;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author yadonghe
 * @date 2020-03-07 15:06
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private SolrClient solrClient;
    @Autowired
    private ItemClients itemClients;
    /**
     * 导入索引库
     */
    @Override
    public void importIndex() throws IOException, SolrServerException {
        //查询所有商品，将每个商品写入索引库
        //调用商品服务进行查询
        List<TbItem> tbItems = itemClients.selectAll();
        for (TbItem item : tbItems) {
            SearchItem searchItem = new SearchItem(item.getId()+"", item.getTitle(), item.getSellPoint(), item.getPrice(), item.getImage());
            solrClient.addBean(searchItem);
            solrClient.commit();
        }

    }
}
