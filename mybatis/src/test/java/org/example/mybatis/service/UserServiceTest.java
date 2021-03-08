package org.example.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.mybatis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String agentId = "013f00be-923d-4a73-8896-43ac0b87d618";
        IPage<User> users = userService.listUser(1, 2, agentId);

        assertEquals(2, users.getRecords().stream().count());
    }

    @Test
    public void testInsertMockUser() {
        for (int i = 0; i < 10; i++) {

        }
    }
}