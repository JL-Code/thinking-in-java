package org.example.annotation.config;

import org.example.annotation.bean.HelloBean;
import org.example.annotation.config.testscope.TestScopeChildrenPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class AnnotationApplicationContextTest {

    /**
     * 测试来自 @Bean 注解的 Bean。
     */
    @Test
    @DisplayName("测试获取由 @Bean 注解的类的实例")
    public void testBeanForAnnotation() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfigurationn.class);
        // 通过 Bean 类型获取 Bean 实例。
        HelloBean beanViaAnnotation = context.getBean(HelloBean.class);
        // 查看当前 context 中存在的 Bean。
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);

        Assertions.assertNotNull(beanViaAnnotation);
    }

    @Test
    @DisplayName("@ComponentSacn 会扫描自身所在包的子包的类")
    public void testComponentSacnChildrenPackage() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfigurationn.class);
        // 通过 Bean 类型获取 Bean 实例。
        TestScopeChildrenPackage beanViaAnnotation = context.getBean(TestScopeChildrenPackage.class);

        Assertions.assertNotNull(beanViaAnnotation);
    }

    @Test
    @DisplayName("@ComponentSacn 会扫描自身所在包的类")
    public void testComponentSacnPeerPackage() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfigurationn.class);
        // 通过 Bean 类型获取 Bean 实例。
        TestScopePeerPackage beanViaAnnotation = context.getBean(TestScopePeerPackage.class);

        Assertions.assertNotNull(beanViaAnnotation);
    }
}