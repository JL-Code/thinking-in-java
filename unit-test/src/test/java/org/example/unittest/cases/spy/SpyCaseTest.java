package org.example.unittest.cases.spy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * <p>创建时间: 2021/5/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@ExtendWith(MockitoExtension.class)
class SpyCaseTest {

    @InjectMocks
    UserService service;

    /**
     * 当你使用spy时，真正的方法会被调用（除非某个方法被存根）。
     */
    @Test
    @DisplayName("调用已stub方法，而不是真实方法")
    public void testSpyRealObject_RealMethodNotCalled() {
        // given
        UserService spy = spy(service);
        String mockedValue = "你好";
        doReturn(mockedValue).when(spy).getUsername();

        // when
        String username = spy.getUsername();

        // then
        // 验证 getUsername 方法被调用1次
        verify(spy, times(1)).getUsername();
        assertEquals(mockedValue, username);
    }

    @Test
    @DisplayName("类方法内嵌调用")
    public void testSpy_ClassMethodEmbeddedCall_Success() {
        // given
        UserService spy = spy(service);
        String mockedValue = "mocked";
        doReturn(mockedValue).when(spy).getUsername();

        // when
        boolean removed = spy.remove();

        // then
        assertTrue(removed);
    }
}