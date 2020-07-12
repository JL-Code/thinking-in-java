package org.example.annotation.bean;

/**
 * <p>创建时间: 2020/7/12 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class LifeCycleTestOfBean {

    /**
     * 对象创建：
     * 单实例：容器启动后创建 Bean 对象
     * 多实例：每次获取 Bean 时创建对象。
     */
    public LifeCycleTestOfBean() {
        System.out.println("LifeCycleTestOfBean ...");
    }

    /**
     * 调用时机：对象创建完成，并且属性赋值完成后，调用 init-method 初始化方法。
     */
    public void init() {
        System.out.println("LifeCycleTestOfBean init ...");
    }

    /**
     * 容器会自动发现并注册 Bean 中的 无参公共 'close' or 'shutdown' 方法作为销毁方法. 若要禁止容器的这种行为
     * 可以通过设置显式指定 destroyMethod=“” 。
     * 调用时机：
     * 单实例：容器关闭的时候调用。
     * 多实例：容器不管理注销方法
     */
    public void close() {
        System.out.println("LifeCycleTestOfBean destroy ...");
    }
}
