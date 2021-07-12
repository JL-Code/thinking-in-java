package org.example.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2021/7/12 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping()
    public User addUser(@RequestBody User user) {
        return user;
    }
}


