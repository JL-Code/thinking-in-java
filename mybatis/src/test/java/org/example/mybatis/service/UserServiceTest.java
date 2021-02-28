package org.example.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.mybatis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/2/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void listUser() {
        IPage<User> users = userService.listUser(1, 2, "75488aad-1692-4f66-a2df-4ae5f5716ae8");

        assertEquals(2, users.getTotal());

    }
}