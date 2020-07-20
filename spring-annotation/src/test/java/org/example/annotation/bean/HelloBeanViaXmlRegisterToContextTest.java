package org.example.annotation.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * <p>描述: 测试 Bean 的加载模式,通过 applicationContext.xml 注册 Bean </p>
 * <p>创建时间: 2020/7/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class HelloBeanViaXmlRegisterToContextTest {

    /**
     * 旧时开发模式，Bean 采取 xml 配置。
     */
    @Test
    public void testGetBeanForXmlConfiguration() {
        String configLocation = "classpath:applicationContext.xml";

        // 创建容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);

        // 通过 Bean name 获取 Bean 实例
        HelloBean bean = (HelloBean) context.getBean("helloBean");

        System.out.println(bean);

        Assertions.assertNotNull(bean);
    }
}