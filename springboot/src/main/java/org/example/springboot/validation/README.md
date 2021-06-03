# Bean Validation

学习思路:

1. 了解 Bean Validation 背景【基本】
2. 了解 Bean Validation 常用注解 【基本】
3. 了解 Bean Validation 基本应用场景【基本】
4. 了解 Bean Validation 的工作原理 【进阶】
5. 类型转换异常如何处理（如 字符串转数字报错）？

## Bean Validation 2.0 44 个内置注解

![image-20210603003651148](http://image.wlinling.com/20210603003651.png)

## 常用注解

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


## 基础验证

PathVariable、RequestParam 参数验证，需要在 Controller 加上 `@Validated` 注解，才能生效，当校验不通过时会抛出 `ConstraintViolationException` 异常。

如下图所示：

> `javax.validation.ConstraintViolationException: getFieldError.fieldName: 个数必须在6和36之间。`

![image-20210603002032850](http://image.wlinling.com/20210603002038.png)

`PathVariable` 参数验证

```java
@GetMapping("/spring/validation/{id}")
public Object methodName(@PathVariable("id") @NotBlank String id) {
  return id;
}
```

`RequestParam` 参数验证

```java
@GetMapping("/spring/validation")
public Object getFieldError(@Size(min = 6, max = 36, message = "字符串长度必须在[6-36]之间") String fieldName) {
  return fieldName;
}
```



## 常见的表单验证场景

1. 必填：@NotBlank
2. 必须是数字
3. 字符长度最小最大限制：@Size
4. 数字范围限制
5. 邮箱 @Email
7. 手机号
8. 身份证
9. 值范围选择

## 进阶

## 分组验证

## 嵌套验证

## 自定义验证

## 命令式验证


