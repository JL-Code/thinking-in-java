package org.example.unittest;

import org.example.unittest.dao.UserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * <p>创建时间: 2021/4/24 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class MockitoTest {
    /**
     * now you can verify interactions
     */
    @Test
    public void testInteractions() {
        // mock creation
        List mockedList = mock(List.class);

        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testStubMethodCalls() {
        // you can mock concrete classes, not only interfaces
        // 您可以模拟具体的类，而不仅仅是接口
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        // 存根出现在实际执行之前
        when(mockedList.get(0)).thenReturn("first");

        // the following prints "first"
        // 以下打印“第一”
        System.out.println(mockedList.get(0));

        // the following prints "null" because get(999) was not stubbed
        // 以下打印为“null”，因为get（999）没有存根
        when(mockedList.get(999)).thenReturn("999");
        System.out.println(mockedList.get(999));
    }

    @Test
    public void testAssertThrows() {

        Exception exception = assertThrows(Exception.class, () ->
        {
            throw new Exception("NPE");
        });

        assertEquals("NPE", exception.getMessage());
    }

    @Test
    public void testVerifyInvocationsOfNumber() {
        // 使用 mock
        List mockedList = mock(List.class);

        mockedList.add("once");
        mockedList.add("twice");
        mockedList.add("twice");

        // times(1) is the default. Therefore using times(1) explicitly can be omitted.
        verify(mockedList).add("once");

        verify(mockedList, times(1)).add("once");
        verify(mockedList, times(2)).add("twice");

    }

    @Test
    @DisplayName("通过 never 方法验证方法从未被调用")
    public void testNever() {
        List mockOne = mock(List.class);
        //using mocks - only mockOne is interacted
        mockOne.add("one");

        //ordinary verification
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        //verify that other mocks were not interacted
//        verifyZeroInteractions(mockTwo, mockThree);
    }

    @Test
    public void testStubbingWithCallbacks() {
        Map mockedMap = mock(Map.class);
        when(mockedMap.get(anyString())).thenAnswer(
                (Answer) invocation -> {
                    Object[] args = invocation.getArguments();
                    Object mock = invocation.getMock();
                    return "called with arguments: " + Arrays.toString(args);
                });

        System.out.println(mockedMap.get("foo"));
        //  doReturn()|doThrow()| doAnswer()|doNothing()|doCallRealMethod() family of methods

    }

    @Test
    public void testMockConstruction() {
        assertTrue(new UserDao().insert());
        try (MockedConstruction mocked = mockConstruction(UserDao.class)) {
            UserDao foo = new UserDao();
            when(foo.insert()).thenReturn(false);
            assertTrue(new UserDao().insert());
            verify(foo).insert();
        }
    }
}
