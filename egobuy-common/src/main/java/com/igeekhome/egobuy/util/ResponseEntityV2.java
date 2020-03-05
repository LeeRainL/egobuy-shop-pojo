package com.igeekhome.egobuy.util;

import com.igeekhome.egobuy.exception.CustomException;

import java.util.List;

/**
 * @author yadonghe
 * @date 2020-02-28 11:25
 */
public class ResponseEntityV2<T> {

    private static final Integer SUCCESS_CODE = 20000;
    private static final Integer FAIL_CODE = 30000;

    private Integer code;
    private String msg;
    private List<T> data;

    public ResponseEntityV2(Integer code, String msg, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseEntityV2() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static ResponseEntityV2 success() {
        return new ResponseEntityV2(SUCCESS_CODE, "成功", null);
    }

    public static ResponseEntityV2 success(List<?> data) {
        return new ResponseEntityV2(SUCCESS_CODE, "成功", data);
    }

    public static ResponseEntityV2 fail() {
        return new ResponseEntityV2(FAIL_CODE, "失败", null);
    }

    public static ResponseEntityV2 fail(CustomException ex) {
        return new ResponseEntityV2(ex.getCode(), ex.getMessage(), null);
    }

    public static ResponseEntityV2 fail(String message) {
        return new ResponseEntityV2(FAIL_CODE, message, null);
    }

    public static ResponseEntityV2 build(Integer code, String msg, List<?> data) {
        return new ResponseEntityV2(code, msg, data);
    }
}
