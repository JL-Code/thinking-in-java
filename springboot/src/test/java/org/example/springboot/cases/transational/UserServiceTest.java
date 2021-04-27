package org.example.springboot.cases.transational;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void addUserIntegrationTest() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("jiangy");

        boolean result = userService.addUser(user);

        assertTrue(result);
    }
}