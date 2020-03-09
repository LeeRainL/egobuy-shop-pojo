package com.egobuy.search.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.util.StringUtils;

/**
 * 写入索引库的实体字段
 * @author yadonghe
 * @date 2020-03-07 15:27
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchItem {
    @Field
    private String id;
    @Field
    private String title;
    @Field
    private String sellPoint;
    @Field
    private Long price;
    @Field
    private String image;


    //private String[] images;

    public String[] getImages() {
        if (StringUtils.isEmpty(image)) {
            return null;
        }
        String[] images = image.split(",");
        if (images != null && images.length > 0) {
            return images;
        }
        return null;
    }

}
