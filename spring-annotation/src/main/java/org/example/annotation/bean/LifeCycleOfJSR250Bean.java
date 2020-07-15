package org.example.annotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <p>创建时间: 2020/7/12 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class LifeCycleOfJSR250Bean{

    @PostConstruct
    public void postConstruct() {
        System.out.println("LifeCycleOfJSR250Bean PostConstruct ...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("LifeCycleOfJSR250Bean PreDestroy ...");
    }
}
