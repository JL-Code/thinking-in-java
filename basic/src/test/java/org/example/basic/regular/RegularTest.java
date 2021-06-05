package org.example.basic.regular;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/5/25 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class RegularTest {

    // TODO: 参数化测试 正则表达式
    static Stream<Arguments> parameterProvider() {
        return Stream.of(
                Arguments.of("test_proc_modifiedViewName [id] [viewName]", 2),
                Arguments.of("select * from sys_user where parameterId = [companyId]", 1),
                Arguments.of("call test_proc_modifiedViewName([id],[viewName])", 2)
        );
    }

    /**
     * TODO: Java 正则表达式 Pattern、Matcher
     */
    @ParameterizedTest
    @MethodSource("parameterProvider")
    void extract(String input, int expected) {

        List<String> extractList = Regular.extract(input);

        assertEquals(expected, extractList.size());
    }
}