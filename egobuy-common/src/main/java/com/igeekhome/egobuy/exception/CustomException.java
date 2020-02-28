package com.igeekhome.egobuy.exception;

/**
 * 自定义异常
 * @author yadonghe
 * @date 2020-02-18 14:54
 */
public class CustomException extends RuntimeException {
    private int code;
    private String message;

    public CustomException(CustomExceptionType customExceptionType, String message){
        this.code = customExceptionType.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
