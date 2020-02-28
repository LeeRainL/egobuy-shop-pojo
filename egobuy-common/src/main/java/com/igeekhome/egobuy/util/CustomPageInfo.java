package com.igeekhome.egobuy.util;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-02-28 10:29
 */

public class CustomPageInfo<T> {
    private List<T> data;
    private Long total;

    public CustomPageInfo(List<T> data, Long total) {
        this.data = data;
        this.total = total;
    }

    public CustomPageInfo() {}

    public List<T> getData() {
        return data;
    }

    public Long getTotal() {
        return total;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
