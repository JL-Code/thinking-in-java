package org.example.springboot.cases.transational.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.springboot.cases.transational.user.User;
import org.example.springboot.cases.transational.dao.UserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    //    @Autowired
//    UserService userService;
//    @Autowired
//    UserDao dao;
    @InjectMocks
    org.example.springboot.cases.transational.user.UserServiceImpl userService;
    @Mock
    UserDao dao;


    @Test
    void addUser() {
    }

    @Test
    @DisplayName("AOP内嵌调用方法导致@Transactional失效")
    void nestedCallTransactionMethod_ThrowException_ThenSuccess() {
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

    @Test
    void remove() {
        String userId = "7cfee64f-4b11-499d-9d19-ec8fdd1700eb";
        LambdaQueryWrapper<User> predicate = Wrappers.<User>lambdaQuery().eq(User::getId, userId);
        // 参数模糊匹配
        // 场景1：引用类型模糊匹配 LambdaQueryWrapper<User> predicate = Wrappers.<User>lambdaQuery().eq(User::getId, userId);、
        // 解决1：使用 Mockito.any(LambdaQueryWrapper.class) 模糊匹配所有的 LambdaQueryWrapper 类型参数。
        when(dao.selectOne(Mockito.any(LambdaQueryWrapper.class))).thenReturn(null);
//        when(dao.selectById(userId)).thenReturn(null);

        boolean removed = userService.remove(userId);

        assertTrue(removed);

    }
}