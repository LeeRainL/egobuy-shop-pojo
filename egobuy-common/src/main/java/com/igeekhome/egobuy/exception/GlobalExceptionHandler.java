package com.igeekhome.egobuy.exception;

import com.igeekhome.egobuy.util.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器   @ControllerAdvice+@ExceptionHandler()
 * @ControllerAdvice；增强Controller,指定当前类是一个全局异常处理器
 * @author yadonghe
 * @date 2020-02-18 15:02
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @ExceptionHandler() 用于声明拦截指定异常
     * 处理自定义异常
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity handleCustomException(CustomException ex) {
        return ResponseEntity.fail(ex);
    }

    /**
     * 对BindException异常进行处理
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity handleBindException(BindException ex) {
        //ex.getBindingResult().getAllErrors()
        //获取异常的所有绑定结果
        String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.fail(new CustomException(CustomExceptionType.USER_ERROR, defaultMessage));
    }



    //除了CustomException之外都进这个方法处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleOtherException(Exception ex) {
        return ResponseEntity.fail(new CustomException(CustomExceptionType.UNKNOW_ERROR, "未知异常"));
    }




    /*
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Map<String, Object> handleNullPointerException(NullPointerException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 300);
        map.put("message", "空指针异常");
        map.put("data", null);
        return map;
    }
    */



}
