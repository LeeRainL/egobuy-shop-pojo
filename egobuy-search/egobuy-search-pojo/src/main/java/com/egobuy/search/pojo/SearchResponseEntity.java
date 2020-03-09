package com.egobuy.search.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yadonghe
 * @date 2020-03-09 11:13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchResponseEntity implements Serializable {
    private Long total;
    private List<SearchItem> items;

}
