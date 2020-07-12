package org.example.annotation.config;

import org.example.annotation.bean.LifeCycleTestOfBean;
import org.example.annotation.bean.TestInitialzingBeanAndDisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>创建时间: 2020/7/12 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
//@Import({LifeCycleTestOfBean.class}) @Import 导入的Bean，无法进一步自定义配置，比如手动指定初始化、销毁等自定义 Bean 生命周期方法
@Import(TestInitialzingBeanAndDisposableBean.class)
public class LifeCycleTestBeanConfiguration {

    /**
     * 容器会自动发现并注册 Bean 中的 无参公共 'close' or 'shutdown' 方法作为销毁方法. 若要禁止容器的这种行为
     * 可以通过设置显式指定 destroyMethod=“” 。
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public LifeCycleTestOfBean lifeCycleTestOfBean() {
        return new LifeCycleTestOfBean();
    }

}
