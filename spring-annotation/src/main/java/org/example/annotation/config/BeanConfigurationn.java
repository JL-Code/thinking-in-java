package org.example.annotation.config;

import org.example.annotation.bean.HelloBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
// @ComponentScan 默认扫描带有 @Component、@Service、@Repository、@Controller 注解的类。扫描范围为：
// @ComponentScan 所在包及子包。
@ComponentScan
public class BeanConfigurationn {

    /**
     * @Bean 默认规定方法名为 Bean ID，方法返回类型为 Bean 类型。
     * @return
     */
    @Bean
    public HelloBean getHelloBean() {
        return new HelloBean();
    }
}
