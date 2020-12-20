package org.example.beautiful.restapi.advice;

import org.example.beautiful.restapi.exception.BusinessException;
import org.example.beautiful.restapi.standard.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RuntimeExceptionControllerAdvice {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Problem> handleBussinessException(BusinessException ex, WebRequest request, HandlerMethod handlerMethod) {

        Problem problem = new Problem();
        problem.setTitle(ex.getMessage());
        problem.setStatus(ex.getStatus());
        problem.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(problem, problem.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Problem> handleRuntimeException(RuntimeException ex, WebRequest request) {

        Problem problem = new Problem();

        problem.setTitle(ex.getMessage());
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(problem, problem.getHttpStatus());
    }

    /**
     * 兜底异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Problem> handleException(Exception ex, ServletWebRequest request) {

        Problem problem = new Problem();

        problem.setTitle(ex.getMessage());
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setPath(request.getRequest().getRequestURI());

        return new ResponseEntity<>(problem, problem.getHttpStatus());
    }
}
