package org.example.beautiful.restapi.exception;

import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

/**
 * 用户未启用异常，当使用未启用的用户的时候会触发这个异常。
 */
public class UserNotEnabledException extends BusinessException {

    public UserNotEnabledException() {
        super();
    }

    public UserNotEnabledException(@NotNull String message) {
        super(message);
    }

    public UserNotEnabledException(@NotNull String message, @NotNull HttpStatus status) {
        super(message, status);
    }
}
