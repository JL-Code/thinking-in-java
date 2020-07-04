package org.example.annotation.config;

import org.example.annotation.controller.HelloController;
import org.example.annotation.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
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
class CustomFilterComponentScanConfigurationTest {

    @Test
    @DisplayName("测试 @ComponentScan 排除 @Controller 扫描")
    public void testMethodName() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CustomFilterComponentScanConfiguration.class);
        // 查看当前 context 中存在的 Bean。
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
        HelloController controller = null;
        HelloService service = null;
        try {
            controller = context.getBean(HelloController.class);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        try {
            service = context.getBean(HelloService.class);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        Assertions.assertNull(controller);
        Assertions.assertNull(service);
    }
}