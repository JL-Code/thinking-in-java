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
        super();
    }

    public BusinessException(@NotNull String title) {
        super(title);
    }

    public BusinessException(@NotNull String title, HttpStatus status) {
        super(title);
        this.status = status;
    }
}
