package org.example.unittest.service;

import org.example.unittest.dao.UserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * <p>创建时间: 2021/4/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    // @InjectMocks 为 UserServiceImpl 模拟一个实现，另外还将 @Mock 标记的依赖模拟注入到其中。
    @InjectMocks
    UserServiceImpl service;
    // @Mock 为 UserDao 模拟一个实现。
    @Mock
    UserDao dao;
//    @Mock
//    UserService userService;

    @Test
    @DisplayName("测试新增用户用例")
    void testSaveUser_Guide() {
        // Given
        when(dao.insert()).thenReturn(true);
        // When
        boolean result = service.save();
        // Then
        assertTrue(result);
    }

    @Test()
    public void testSaveUser_ThrowException() {

        doThrow(new Exception()).when(dao).insert();

        assertThrows(Exception.class, () -> {
            dao.insert();
        });
    }
}