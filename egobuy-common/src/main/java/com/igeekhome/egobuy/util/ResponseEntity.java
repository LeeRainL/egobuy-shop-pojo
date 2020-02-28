package com.igeekhome.egobuy.util;

import com.igeekhome.egobuy.exception.CustomException;

/**
 * @author yadonghe
 * @date 2020-02-28 11:25
 */
public class ResponseEntity {

    private static final Integer SUCCESS_CODE = 20000;
    private static final Integer FAIL_CODE = 30000;

    private Integer code;
    private String msg;
    private Object data;

    public ResponseEntity(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseEntity() {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseEntity success() {
        return new ResponseEntity(SUCCESS_CODE, "成功", null);
    }

    public static ResponseEntity success(Object data) {
        return new ResponseEntity(SUCCESS_CODE, "成功", data);
    }

    public static ResponseEntity fail() {
        return new ResponseEntity(FAIL_CODE, "失败", null);
    }

    public static ResponseEntity fail(CustomException ex) {
        return new ResponseEntity(ex.getCode(), ex.getMessage(), null);
    }

    public static ResponseEntity build(Integer code, String msg, Object data) {
        return new ResponseEntity(code, msg, data);
    }
}
