package org.example.annotation.bean.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模拟循环应用的 Bean
 */
public class CircularReferenceBean {
    private String propName = "循环引用";
    @Autowired
    private MessageBean messageBean;

    public CircularReferenceBean() {
        System.out.println("CircularReferenceBean...");
    }
    
}
