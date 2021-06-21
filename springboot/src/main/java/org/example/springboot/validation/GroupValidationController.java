package org.example.springboot.validation;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

@Api(tags = "分组校验")
@RestController
public class GroupValidationController {

    @Operation(operationId = "模拟新增验证")
    @PostMapping("/bean/group-validation")
    public Object postGroupValidation(@Validated({GroupValidationModel.Insert.class, Default.class})
                                      @RequestBody GroupValidationModel model) {
        return model;
    }

    @Operation(operationId = "模拟更新验证")
    @PutMapping("/bean/group-validation/default")
    public Object putGroupValidation(@Validated @RequestBody GroupValidationModel model) {
        return model;
    }

    @Operation(operationId = "模拟更新验证")
    @PutMapping("/bean/group-validation/update-and-default")
    public Object putGroupValidationUpdateDefault(@Validated({GroupValidationModel.Update.class}) @RequestBody GroupValidationModel model) {
        return model;
    }
}
