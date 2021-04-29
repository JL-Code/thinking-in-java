package org.example.basic.uitl;

import org.springframework.beans.BeanUtils;

/**
 * <p>创建时间: 2021/4/29 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class BeanUtilsCase {
    public User copyProperties(UserDTO dto, User user) {
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
