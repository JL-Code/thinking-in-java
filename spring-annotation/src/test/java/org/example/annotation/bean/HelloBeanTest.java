package org.example.annotation.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * <p>描述: 测试 Bean 的加载模式 </p>
 * <p>创建时间: 2020/7/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class HelloBeanTest {

    /**
     * 旧时开发模式，Bean 采取 xml 配置。
     */
    @Test
    public void testGetBeanForXmlConfiguration() {
        String configLocation = "classpath:beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        HelloBean bean = (HelloBean) context.getBean("helloBean");
        HelloBean bean2 = context.getBean(HelloBean.class);

        System.out.println(bean);

        String[] names = context.getBeanDefinitionNames();

        Arrays.stream(names).forEach(System.out::println);

        Assertions.assertNotNull(bean);
        Assertions.assertNotNull(bean2);
    }
}