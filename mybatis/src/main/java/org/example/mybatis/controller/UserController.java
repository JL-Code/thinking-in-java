package org.example.mybatis.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.mybatis.model.User;
import org.example.mybatis.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2021/2/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public IPage<User> listUser(long page, long size, String agentId) {
        return userService.listUser(page, size, agentId);
    }
}
