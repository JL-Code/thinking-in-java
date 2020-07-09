package org.example.annotation.config;

import org.example.annotation.dao.HelloDao;
import org.example.annotation.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class AutowiredOfConfigurationTest {

    ApplicationContext context =
            new AnnotationConfigApplicationContext(AutowiredOfConfiguration.class);

    @BeforeEach
    public void beforeEach() {
        System.out.println("当前 ApplicationContext 中存在的 Bean：");
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
        System.out.println("=========================↑↑↑↑↑↑↑=========================");
    }

    @Test
    public void testMethodName() {
        HelloService service = context.getBean(HelloService.class);
        System.out.println(service);
        Assertions.assertNotNull(service.getHelloDao());
    }
}