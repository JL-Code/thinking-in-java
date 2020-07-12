package org.example.annotation.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p>创建时间: 2020/7/12 </p>
 * 实现了 InitializingBean、DisposableBean，来为 Bean 提供初始化和销毁生命周期方法。
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */

public class TestInitialzingBeanAndDisposableBean implements InitializingBean, DisposableBean {
    /**
     * 在 Bean 的属性赋值后调用
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet ...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy ...");
    }
}
