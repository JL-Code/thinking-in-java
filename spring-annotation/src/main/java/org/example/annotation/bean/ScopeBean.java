package org.example.annotation.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/5 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class ScopeBean {

    public ScopeBean() {
        System.out.println("scopebean construct...");
    }

    @PostConstruct
    public void init() {
        System.out.println("scopebean init...");
    }

    @PreDestroy
    public void destory() {
        System.out.println("scopebean destory...");
    }
}
