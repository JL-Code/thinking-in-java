package org.example.annotation.config;

import org.example.annotation.bean.HelloBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <p>描述: 使用不同的 @Scope 注释 Bean，并观察之间的区别 </p>
 * <p>创建时间: 2020/7/5 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
public class DifferentScopeLifecycleOfBeanConfiguration {

    @Bean
    public HelloBean defaultSingleton() {
        return new HelloBean();
    }

    @Bean
    @Scope("prototype")
    public HelloBean specifiedPrototype() {
        return new HelloBean();
    }
}
