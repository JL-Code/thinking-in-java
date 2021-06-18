package org.example.springboot.validation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间: 2021/6/18 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestControllerAdvice
public class ExcetpionInterceptor {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    public Map<String, Object> handleBindException(BindException ex) {
        Map<String, String> erros = new HashMap<>(16);
        Map<String, Object> result = new HashMap<>(16);
        BindingResult bindingResult = ex.getBindingResult();

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> erros.put(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        result.put("errcode", HttpStatus.BAD_REQUEST.value());
        result.put("errmsg", "客户端请求参数错误");
        result.put("detail", erros);

        return result;
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>(16);
        Map<String, Object> result = new HashMap<>(16);
        BindingResult bindingResult = ex.getBindingResult();

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> erros.put(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        result.put("errcode", HttpStatus.BAD_REQUEST.value());
        result.put("errmsg", "客户端请求参数错误");
        result.put("detail", erros);

        return result;
    }
}
