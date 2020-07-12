package org.example.annotation.config;

import org.example.annotation.bean.ProductFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
public class TestFactoryBeanOfBeanConfiguration {

    @Bean
    public ProductFactory productFactoryBean() {
        return new ProductFactory();
    }
}
