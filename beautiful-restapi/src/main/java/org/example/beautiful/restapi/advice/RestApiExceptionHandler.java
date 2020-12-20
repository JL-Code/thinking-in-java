package org.example.beautiful.restapi.advice;

import org.example.beautiful.restapi.standard.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Problem problem = new Problem();
        problem.setTitle("请求参数未通过验证！");
        problem.setStatus(status);
        problem.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        // 从异常对象中拿到ObjectError对象
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuilder detail = new StringBuilder();

        for (ObjectError error : errors) {
            detail.append(String.format("%s; ", error.getDefaultMessage()));
        }

        problem.setDetail(detail.toString());

        return new ResponseEntity<>(problem, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Problem problem = new Problem();
        problem.setTitle(status.getReasonPhrase());
        problem.setStatus(status);
        problem.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        return new ResponseEntity<>(problem, status);
    }

}
