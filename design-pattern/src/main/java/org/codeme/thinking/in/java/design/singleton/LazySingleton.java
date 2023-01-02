package org.codeme.thinking.in.java.design.singleton;

/**
 * <p>描述: 懒汉式单例 用的时候才实例化。 </p>
 * <p>创建时间: 2020/6/25 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class LazySingleton {

    private int counter = 0;
    private static LazySingleton instance = null;

    private LazySingleton() {
        System.out.println("LazySingleton ctor ...");
    }

    /**
     * 线程不安全版本
     *
     * @return
     */
    public static LazySingleton getInstance() {
        if (instance == null) {
            System.out.println("instance == null...");
            instance = new LazySingleton();
        }
        return instance;
    }

    public int getCounter() {
        return counter;
    }
}
