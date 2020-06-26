package org.example.design.singleton;

/**
 * <p>描述: 单例模式 </p>
 * <p>创建时间: 2020/6/25 </p>
 * <p>
 * 懒汉式单例
 * 饿汉式单例
 * <p>
 * 考虑：
 * 1. 多线程安全
 * 2. 实例初始方式（是否懒加载）
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Singleton {

    private static int counter = 0;
    private static Singleton instance = null;

    private Singleton() {
    }

    /**
     * 线程不安全版本
     *
     * @return
     */
    public static Singleton getInstance() {
        counter++;
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public int getCounter() {
        return counter;
    }
}
