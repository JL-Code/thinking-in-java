package org.example.annotation.config;

import org.example.annotation.bean.HelloBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * <p>描述: 测试 Bean 在指定不同 @Scope 时生命周期间的区别。 </p>
 * <p>创建时间: 2020/7/5 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class DifferentScopeLifecycleOfBeanConfigurationTest {
    ApplicationContext context =
            new AnnotationConfigApplicationContext(DifferentScopeLifecycleOfBeanConfiguration.class);

    @BeforeEach
    public void beforeEach() {
        System.out.println("当前 ApplicationContext 中存在的 Bean：");
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);

    }

    @Test
    @DisplayName("测试 @Scope 默认作用域 singleton,期望每次获取到的 Bean 都是同一个实例")
    public void testSingletonForDefaultScope() {
        HelloBean singleton1 = (HelloBean) context.getBean("defaultSingleton");
        HelloBean singleton2 = (HelloBean) context.getBean("defaultSingleton");

        Assertions.assertNotNull(singleton1);
        Assertions.assertEquals(singleton1, singleton2);
    }

    @Test
    @DisplayName("测试 @Scope 作用域 prototype, 期望每次获取到的 Bean 都不是同一个实例")
    public void testPrototypeForSpecifiedScope() {
        HelloBean singleton1 = (HelloBean) context.getBean("specifiedPrototype");
        HelloBean singleton2 = (HelloBean) context.getBean("specifiedPrototype");

        Assertions.assertNotNull(singleton1);
        Assertions.assertNotEquals(singleton1, singleton2);
    }
}