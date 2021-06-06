# Bean Validation

## 1. 学习思路

1. 了解 Bean Validation 背景【基本】
2. 了解 Bean Validation 常用注解 【基本】
3. 了解 Bean Validation 基本应用场景【基本】
4. 了解 Bean Validation 的工作原理 【进阶】
5. 类型转换异常如何处理（如 字符串转数字报错）

## 2. 背景

## 3. 解决的问题

## 4. 基础使用

**常用注解**

| 注解      | 含义                                                         | 支持的类型                                                   | 示例                                                        | 来源                           |
| --------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ----------------------------------------------------------- | ------------------------------ |
| @Null     |                                                              |                                                              |                                                             | `Bean Validation 1.0 (JSR303)` |
| @NotNull  | 被注解的元素必须不能是 null。                                | 引用类型                                                     |                                                             |                                |
| @NotEmpty | 被注解的元素必须不能是 null 或 empty（可以是空格字符）。     | String\|Collection\|Map\|Array                               |                                                             | `Bean Validation 2.0 （JSR380` |
| @NotBlank | 被注解的元素不能是 null，必须至少包含一个非空格字符。        | String                                                       |                                                             | `Bean Validation 2.0 （JSR380` |
| @Feture   | 被注解的元素必须是将来的瞬间、日期或时间。                   | 时间                                                         |                                                             |                                |
| @Length   | 验证字符串是否在最小和最大包含之间。                         | String                                                       | `@Length(min=0 max=50 message="字符串长度在 0-50 之间")`    | `Hibernate Validator`          |
| @Size     | 被注解的元素大小必须在指定的边界（包括）之间。               | String\|Collection\|Map\|Array                               | `@Size(min = 6, max = 36 message="字符串长度在 6-36 之间")` | `Bean Validation 2.0 （JSR380` |
| @Email    | 字符串必须是一个格式良好的电子邮件地址                       | String                                                       | `@Email(message="邮箱格式非法")`                            |                                |
| @Range    |                                                              |                                                              |                                                             |                                |
| @Min      | 被注解的元素必须是一个数字，其值必须高于或等于指定的最小值。 | BigDecimal、BigInteger、byte, short, int, long, 以及其 各自包装类 |                                                             | `Bean Validation 2.0 （JSR380` |
| @Max      | 被注解的元素必须是一个数字，其值必须小于或等于指定的最大值。 | BigDecimal、BigInteger、byte, short, int, long, 以及其 各自包装类 |                                                             | `Bean Validation 2.0 （JSR380` |



PathVariable、RequestParam 参数验证，需要在 Controller 加上 `@Validated` 注解，才能生效，当校验不通过时会抛出 `ConstraintViolationException` 异常。

如下图所示：

> `javax.validation.ConstraintViolationException: getFieldError.fieldName: 个数必须在6和36之间。`

![image-20210603002032850](http://image.wlinling.com/20210603002038.png)

`PathVariable` 参数验证

```java
@Validated
@RestController
public class SpringValidationController {
  @GetMapping("/spring/validation/{id}")
  public Object methodName(@PathVariable("id") @NotBlank String id) {
    return id;
  }
}
```

`RequestParam` 参数验证

```java
@Validated
@RestController
public class SpringValidationController {
  @GetMapping("/spring/validation")
  public Object getFieldError(@Size(min = 6, max = 36, message = "字符串长度必须在[6-36]之间") String fieldName) {
    return fieldName;
  }
}
```

`RequestBody` 参数验证

> 使用 `@Validated` 标注 `UserVO` 类型参数并紧跟 BindingResult 参数， SpringValidation 验证机制生效。
>
> `BindingResult` 类型参数用于接收参数绑定时 Validation 的结果。
>
> 1. 通过 `hasErrors ` 方法获取验证结果是否有错误信息。
> 2. 通过 `getFieldErrors` 方法获取具体的字段错误信息。

```java
@RestController
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

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> erros.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return erros;
        }

        return uesrVO;
    }
}
```



## 常见的表单验证场景

1. 必填
2. 必须是数字
3. 字符长度限制
4. 数字大小限制
5. 邮箱
7. 手机号
8. 身份证

## 进阶

## 分组验证

应用场景1：解决编辑与新增实体验证规则不一致

> 对于实体 `User` 在编辑时需要强制提供 `id` 标识，但是新增时则不是必须的。

解决思路：利用 `JSR380` 规范提供的 `group `属性，我们针对编辑、新增的验证规则进行分组，在验证时选择性的指定生效的分组，从而达到验证规则隔离。

具体步骤：

1. 声明验证分组
2. 声明验证注解并指定验证分组
3. 选择要进行验证的验证分组

```java
package org.example.springboot.validation;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/bean/group-validation")
    public Object putGroupValidation(@Validated({GroupValidationModel.Update.class, Default.class})
                                     @RequestBody GroupValidationModel model) {
        return model;
    }
}
```



```java
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

    // 分组验证标识 Insert
    public interface Insert {
    }
    // 分组验证标识 Update
    public interface Update {
    }
}
```



> - 配置分组的时候，记得不要漏掉默认分组 `Default.class`，否则就只会校验 `groups = {Update.class}`的规则。

## 嵌套验证

## 自定义验证

## 命令式验证



