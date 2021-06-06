package org.example.springboot.validation;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class GroupValidationModel {
    // 按照 新增、修改
    @NotNull(groups = Update.class, message = "ID 不能为空")
    private String id;
    @NotBlank(message = "用户名不能为空")
    private String name;

    // 分组验证标识
    public interface Insert {
    }

    public interface Update {
    }
}
