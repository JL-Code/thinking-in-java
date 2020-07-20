package org.example.basic.inherit;

/**
 * <p>创建时间: 2020/7/20 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Sub extends Base {
    // 子类变量赋值需待父类构造方法调用后
    private String className = "Sub";

    public Sub() {

    }

    @Override
    public void outputClassName() {
        // 重写父类方法
        System.out.println(className);
    }
}
