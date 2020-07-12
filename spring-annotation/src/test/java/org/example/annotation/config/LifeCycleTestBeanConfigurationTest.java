package org.example.annotation.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2020/7/12 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class LifeCycleTestBeanConfigurationTest {
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(LifeCycleTestBeanConfiguration.class);

    @BeforeEach
    public void beforeEach() {
        System.out.println("当前 ApplicationContext 中存在的 Bean：");
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);

    }

    @Test
    public void testMethodName() {
        // 关闭容器
        context.close();
    }
}