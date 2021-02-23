package org.example.annotation.config;

import org.example.annotation.bean.OneInterface;
import org.example.annotation.bean.TwoInterface;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/1/31 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class TestSingleClassMultipleInterfaceConfigTest {
    ApplicationContext context =
            new AnnotationConfigApplicationContext(TestSingleClassMultipleInterfaceConfig.class);

    @Test
    public void testPrintBeans() {
        // 查看当前 context 中存在的 Bean。
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
    }

    @Test
    public void testSingleClassMultipleInterface() {
        OneInterface one = context.getBean(OneInterface.class);
        TwoInterface two = context.getBean(TwoInterface.class);

        assertNotNull(one);
        assertNotNull(two);
    }
}