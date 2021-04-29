package org.example.springboot.cases.transational;

import org.example.springboot.cases.transational.dao.UserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserDao dao;

    @Test
    void addUserIntegrationTest() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("jiangy");

        boolean result = userService.addUser(user);

        assertTrue(result);
    }

    @Test
    @DisplayName("AOP内嵌调用方法导致@Transactional失效")
    void nestedCallTransactionMethod_ThrowException_Success() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("jiangy");

        assertThrows(RuntimeException.class, () -> {
            userService.nestedCallTransactionMethod(user);
        });

        User result = dao.selectById(user.getId());

        // user 被成功保存了。
        assertNotNull(result);
    }
}