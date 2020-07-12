package org.example.annotation.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class ProductFactory implements FactoryBean<Product> {

    public ProductFactory() {
        System.out.println("ProductFactory...");
    }

    /**
     * Bean 实例
     *
     * @return
     * @throws Exception
     */
    @Override
    public Product getObject() throws Exception {
        return new Product("chips");
    }

    /**
     * 返回类型
     *
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Product.class;
    }

    /**
     * 是否是单例
     * true： 表示该 Bean 为单例模式，在 Spring 容器中只有一个实例。
     * false： 表示该 Bean 不是单例模式，在 Spring 容器每次获取时都会重新创建实例。
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
