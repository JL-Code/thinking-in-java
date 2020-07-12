package org.example.annotation.config;

import org.example.annotation.bean.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2020/7/12 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class TestFactoryBeanOfBeanConfigurationTest {
    ApplicationContext context =
            new AnnotationConfigApplicationContext(TestFactoryBeanOfBeanConfiguration.class);

    @BeforeEach
    public void beforeEach() {
        System.out.println("当前 ApplicationContext 中存在的 Bean：");
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);

    }

    @Test
    @DisplayName("注册的Bean名称是productFactoryBean，实际获取到的Bean类型为Product")
    public void testProductFactoryBean() {
//        Object product = context.getBean("productFactoryBean");
//        Product product1 = context.getBean(Product.class);
//
//        System.out.println(product);
//        System.out.println(product1);
//
//        Assertions.assertEquals(product.getClass(), Product.class);
//        Assertions.assertEquals(product1.getClass(), Product.class);
    }

    @Test
    public void testMethodName() throws IOException {
        String fileName = "", line = "";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        line = bufferedReader.readLine();
        while (line.length()!=0){
            line = bufferedReader.readLine();
        }
    }
}