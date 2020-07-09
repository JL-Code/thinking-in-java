package org.example.annotation.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/5 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class CustomFilterTypeTest {
    @Test
    public void testMethodName() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CustomFilterComponentScanConfiguration.class);
        // 查看当前 context 中存在的 Bean。
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
    }
}