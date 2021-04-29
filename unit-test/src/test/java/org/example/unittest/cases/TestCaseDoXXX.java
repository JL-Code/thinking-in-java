package org.example.unittest.cases;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

/**
 * <p>创建时间: 2021/4/29 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class TestCaseDoXXX {

    @Mock
    doXXXCase doXXXCase;

    @Test
    void noReturnMethod() {
        // Stub 一个 RuntimeException
        doThrow(RuntimeException.class).when(doXXXCase).noReturnMethod();
    }
}