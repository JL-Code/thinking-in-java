package org.example.unittest.cases.argumentmatcher;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * <p>创建时间: 2021/5/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class ArgumentMatcherCaseTest {

    @Test
    public void test_ArgumentMatcher_Exact() {
        // given
        Map mockMap = Mockito.mock(Map.class);
        when(mockMap.get(11)).thenReturn(111); // 基础类型
        when(mockMap.get(Arrays.asList("袁紫霞"))).thenReturn("白玉京"); // 引用类型
        // when
        Object mapResult1 = mockMap.get(11);
        Object mapResult2 = mockMap.get(Arrays.asList("袁紫霞"));
        // then
        assertEquals(111, mapResult1);
        assertEquals("白玉京", mapResult2);
    }

    @Test
    public void test_ArgumentMatcher_Fuzzy() {
        Map mockMap = Mockito.mock(Map.class);

        Mockito.when(mockMap.get(Mockito.endsWith("天"))).thenReturn("龙傲天"); // 字符串。eg:以天结尾的
        Mockito.when(mockMap.get(Mockito.anyLong())).thenReturn(999L); // 基础类型. eg:任何long类型
        Mockito.when(mockMap.get(Mockito.any(User.class))).thenReturn(new User()); // 引用类型

        assertEquals("龙傲天", mockMap.get("星期天"));
        assertEquals(999L, mockMap.get(1L));
        assertEquals(new User(), mockMap.get(new User()));
    }
}