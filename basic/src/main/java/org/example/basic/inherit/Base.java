package org.example.basic.inherit;

/**
 * <p>创建时间: 2020/7/20 </p>
 * ref: https://blog.csdn.net/xidiancoder/article/details/56276155
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Base {
    private String className = "Base";

    public Base() {
        outputClassName();
    }

    public void outputClassName() {
        System.out.println(className);
    }
}
