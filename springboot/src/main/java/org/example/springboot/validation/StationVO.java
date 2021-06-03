package org.example.springboot.validation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * <p>创建时间: 2021/6/2 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class StationVO {
    @NotEmpty
    @Length(min = 36, max = 36)
    private String id;

    @NotEmpty
    @Length(min = 0, max = 50)
    private String name;
}
