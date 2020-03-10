package com.egobuy.egobuysearchservice.service.impl;

import com.egobuy.egobuysearchservice.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author yadonghe
 * @date 2020-03-10 11:18
 */
@Service
public class MessageService {

    @Autowired
    private IndexService indexService;

    @JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListenerContainerFactory")
    public void addIndex(String id) {
        try {
            //稍微延迟一会儿，保证页面静态化时，数据能够成功保存到数据库
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        indexService.addIndex(Long.parseLong(id));
    }
}
