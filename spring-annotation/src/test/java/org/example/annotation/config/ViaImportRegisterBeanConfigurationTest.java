package org.example.annotation.config;

import org.example.annotation.bean.ScopeBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * <p>描述: 通过 @Import 注解注册 Bean 的配置 </p>
 * <p>创建时间: 2020/7/5 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class ViaImportRegisterBeanConfigurationTest {

    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ViaImportRegisterBeanConfiguration.class);

    /**
     * 当前测试类中每个测试方法执行前执行一次。
     */
    @BeforeEach
    public void beforeEach() {
        System.out.println("当前 ApplicationContext 中存在的 Bean：");
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);

    }

    @Test
    public void testMethodName() {
        ScopeBean bean1 = context.getBean(ScopeBean.class);
        ScopeBean bean2 = context.getBean(ScopeBean.class);
    }

    /**
     * 当前测试类中每个测试方法执行后执行一次
     */
    @AfterEach
    public  void afterEach() {
        // 关闭应用上下文
        context.close();
    }
}
