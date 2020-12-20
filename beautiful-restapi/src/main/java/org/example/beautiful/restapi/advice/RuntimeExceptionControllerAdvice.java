package org.example.beautiful.restapi.advice;

import org.example.beautiful.restapi.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.ModelAndViewContainer;

@RestControllerAdvice
public class RuntimeExceptionControllerAdvice {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBussinessException(BusinessException ex, WebRequest request, HandlerMethod handlerMethod) {
        String methodName = handlerMethod.getMethod().getName();
        return new ResponseEntity<>(ex.getMessage() + "  " + methodName, ex.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e) {
        return e.getMessage();
    }

    /**
     * 兜底异常处理
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return e.getMessage();
    }
}
