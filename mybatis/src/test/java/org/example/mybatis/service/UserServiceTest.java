package org.example.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.mybatis.model.User;
import org.example.mybatis.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
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
    @Autowired
    UserRepository userRepository;

    @Test
    void listUser() {
        String agentId = "013f00be-923d-4a73-8896-43ac0b87d618";
        IPage<User> users = userService.listUser(1, 2, agentId);
        assertEquals(2, users.getRecords().stream().count());
    }

    @Test
    public void testInsertMockUser() {
        ArrayList<User> users = new ArrayList<>();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.allEq()
        userRepository.selectList(queryWrapper);
    }
}