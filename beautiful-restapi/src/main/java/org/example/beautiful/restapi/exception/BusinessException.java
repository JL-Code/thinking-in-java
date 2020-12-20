package org.example.beautiful.restapi.exception;

import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

/**
 * 业务异常基类，所有的自定义业务异常都应该继承此类
 */
public class BusinessException extends RuntimeException {
    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public BusinessException() {
        this("");
    }

    public BusinessException(@NotNull String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BusinessException(@NotNull String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
