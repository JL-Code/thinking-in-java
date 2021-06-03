package org.example.springboot.validation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>创建时间: 2021/5/31 </p>
 * <p>
 * 常见的表单验证场景
 * 1. 必填
 * 2. 必须是数字
 * 3. 字符长度最小最大限制
 * 4. 数字范围限制
 * 5. 邮箱
 * 7. 手机号
 * 8. 身份证
 * 9. 值范围选择
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class UserVO {
    /**
     * TODO: 场景1：编辑与新增的入参都使用 UserVO ，在两种情况下验证规则可能会不同，例如：ID 字段在新增时可以不填写，但是在编辑时必须填写。
     * 解决方案：采用 groups 分组验证策略，将新增与编辑的验证规则隔离开来，让验证规则独立生效互不影响。
     */
    @NotBlank(message = "不能为 Null", groups = Update.class)
    private String id;

    @Length(max = 50, message = "字符长度在 0-50 之间")
    private String name;

    // 邮箱
    @Email(message = "邮箱格式错误")
    @NotBlank
    private String email;

    @Range(min = 0, max = 150, message = "年龄的范围必须是 0-150")
    private Integer age;

    @Min(value = 0, message = "年龄最小0")
    @Max(value = 150, message = "年龄最大150")
    private Integer age1;

    private LocalDateTime created;

    //    @Pattern(regexp = "^[1|2|3]$", message = "只能输入 1、2、3")
    private SexEnum sex;

    private String phoneNumber;

    @Pattern(regexp = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$", message = "身份证格式非法")
    private String card;

    @NotNull
    private Boolean enabled;

    @Valid
    private List<StationVO> stations;

    public interface Update {  }
}
