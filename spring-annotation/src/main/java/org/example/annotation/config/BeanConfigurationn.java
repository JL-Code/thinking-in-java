package org.example.annotation.config;

import org.example.annotation.bean.HelloBean;
import org.example.annotation.bean.ScopeBean;
import org.springframework.context.annotation.*;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
// @ComponentScan 默认扫描带有 @Component、@Service、@Repository、@Controller 注解的类。
// 扫描范围为: @ComponentScan 所注释的类的当前包及其子包。
@ComponentScan(value = "org.example.annotation")
public class BeanConfigurationn {

    /**
     * @return {@link HelloBean}
     * @Bean 注解默认规定方法名为 Bean ID，方法返回类型为 Bean 类型。
     * 默认为单例模式，随着 ApplicationContext 创建而创建，但可以通过 @Lazy 注解改变这种行为，
     * 将 Bean 的实例化延迟到第一次调用时。
     */
    @Bean
    public HelloBean getHelloBean() {
        return new HelloBean();
    }

    /**
     * 默认单例的Bean 是在创建 ApplicationContext 时创建，可以通过 @Lazy 注解改变这种行为，
     * 将 Bean 的实例化延迟到第一次调用时。
     *
     * @return
     */
    @Lazy
    public HelloBean getLazyHelloBean() {
        return new HelloBean();
    }

    /**
     * Scope: singleton（默认）、prototype、request、session
     */
    @Bean
    @Scope(value = "prototype")
    public ScopeBean getScopeBean() {
        return new ScopeBean();
    }
}
