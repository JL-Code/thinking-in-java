package org.example.unittest.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * <p>创建时间: 2021/4/29 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class UserDaoTest {

    @Mock
    UserDao dao;

    @BeforeAll
    public void init() {
        // 等同于 @ExtendWith(MockitoExtension.class)
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insert() {
        when(dao.insert()).thenReturn(true);
        boolean result = dao.insert();
        assertTrue(result);
    }
}