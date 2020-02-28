package com.igeekhome.egobuy.exception;

/**
 * 模型视图异常：用于返回页面的处理器抛出的自定义异常
 * @author yadonghe
 * @date 2020-02-20 09:13
 */
public class ModelViewException extends RuntimeException {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 实现将CustomeException转换为ModelViewException
     * @param customEx
     * @return
     */
    public static ModelViewException transfer(CustomException customEx) {
        ModelViewException modelViewException = new ModelViewException();
        modelViewException.setCode(customEx.getCode());
        modelViewException.setMessage(customEx.getMessage());
        return modelViewException;
    }

}
