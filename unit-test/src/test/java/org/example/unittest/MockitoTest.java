package org.example.unittest;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

}
