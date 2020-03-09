package com.egobuy.egobuysearchservice.service.impl;

import com.egobuy.egobuysearchservice.service.IndexService;
import com.egobuy.search.pojo.SearchItem;
import com.egobuy.search.pojo.SearchResponseEntity;
import com.igeekhome.egobuy.feign.clients.ItemClients;
import com.igeekhome.shop.pojo.TbItem;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public SearchResponseEntity select(String keyword, Integer page, Integer limit) {
        SearchResponseEntity responseEntity = new SearchResponseEntity();

        SolrQuery query = new SolrQuery("title:"+keyword);
        //分页
        query.setStart((page - 1) * limit);
        query.setRows(limit);
        //高亮设置
        query.setHighlight(true);
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");
        query.addHighlightField("title");

        List<SearchItem> items = null;
        try {
            QueryResponse response = solrClient.query(query);
            //获取总条数
            long numFound = response.getResults().getNumFound();
            responseEntity.setTotal(numFound);
            items = response.getBeans(SearchItem.class);
            //获取高亮字段
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            for (SearchItem item : items) {
                List<String> titles = highlighting.get(item.getId()).get("title");
                if (titles != null && titles.size() > 0) {
                    item.setTitle(titles.get(0));//取出高亮字段的值设置给item对象
                }
            }

            responseEntity.setItems(items);

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }
}
