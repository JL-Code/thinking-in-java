package org.example.basic.model.mapper.mapstruct;

import lombok.Data;

/**
 * <p>创建时间: 2021/6/18 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String controlNode;
}
