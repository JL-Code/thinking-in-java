package org.example.beautiful.restapi.entity;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Data
public class User {

    private Integer id;

    @NotNull(message = "姓名不能为空")
    @Size(min = 6, max = 11, message = "姓名长度必须是6-11个字符")
    private String name;

    @Email(message = "邮箱格式不正确")
    @NotNull(message = "邮箱不能为空")
    private String email;
}
