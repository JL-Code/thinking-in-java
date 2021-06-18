package org.example.springboot.validation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import javax.validation.groups.Default;

@Data
public class GroupValidationModel {

    @NotBlank(groups = Update.class)
    private String id;

    @NotBlank(groups = Update.class)
    private String id2;

    @NotBlank(groups = Update.class)
    private String id3;

    @NotBlank(groups = Update.class)
    private String modifer;


    @NotBlank(groups = Insert.class)
    private String creator;

    @NotBlank(message = "用户名不能为空")
    @Length(max = 50)
    private String name;

    /**
     * min 默认 0
     * max 应该可以从 column info 中获取 length
     * message @Length 的默认值： 长度必须在min和max之间；@Size 的默认值： 个数必须在min和max之间；
     */
//    @Size(max = 10, message = "长度必须在0和10之间")
//    @Length(max = 50)
    @Size(max = 50)
    private String remark;

    // 分组验证标识
    public interface Insert {
    }

    public interface Update extends Default {
    }
}
