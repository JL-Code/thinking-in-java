package org.example.springboot.validation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间: 2021/5/31 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
@Api(tags = "SpringValidation")
@Validated
public class SpringValidationController {

    /**
     * Bean Validation 基础验证
     *
     * @param uesrVO
     * @param bindingResult
     * @return
     */
    @ApiOperation("Bean Validation 基础验证")
    @PostMapping("/spring/validation/basic")
    public Object validation(@Validated @RequestBody UserVO uesrVO, BindingResult bindingResult) {

        Map<String, String> erros = new HashMap<>(16);

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        fieldErrors.forEach(fieldError -> erros.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return erros;

    }

    @ApiOperation("Bean Validation 基础验证")
    @PutMapping("/spring/validation/basic")
    public Object validationPut(@Validated @RequestBody UserVO uesrVO, BindingResult bindingResult) {

        Map<String, String> erros = new HashMap<>(16);

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        fieldErrors.forEach(fieldError -> erros.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return erros;

    }

    @GetMapping("/spring/validation/{id}")
    public Object methodName(@PathVariable("id") @NotBlank String id) {
        return id;
    }

    @GetMapping("/spring/validation")
    public Object getFieldError(@Size(min = 6, max = 36, message = "字符串长度必须在[6-36]之间") String fieldName) {
        return fieldName;
    }
}
