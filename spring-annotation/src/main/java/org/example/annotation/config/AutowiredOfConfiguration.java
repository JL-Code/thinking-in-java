package org.example.annotation.config;

import org.example.annotation.dao.AppDao;
import org.example.annotation.dao.HelloDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
@ComponentScan({"org.example.annotation.dao", "org.example.annotation.service"})
public class AutowiredOfConfiguration {

    @Bean
    public HelloDao helloDao2() {
        return new HelloDao("label2");
    }

    @Primary
    @Bean
    public AppDao appDao2() {
        return new AppDao("app2");
    }
}
