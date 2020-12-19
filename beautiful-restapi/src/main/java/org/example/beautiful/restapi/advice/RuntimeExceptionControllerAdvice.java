package org.example.beautiful.restapi.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RuntimeExceptionControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public String NullPointerExceptionHandler(NullPointerException e) {
        return "NPE";
    }

    @ExceptionHandler(RuntimeException.class)
    public String RuntimeExceptionHandler(RuntimeException e) {
        return e.getMessage();
    }

    /**
     * 兜底异常处理
     */
    @ExceptionHandler(Exception.class)
    public String ExceptionHandler(Exception e) {
        return e.getMessage();
    }
}
