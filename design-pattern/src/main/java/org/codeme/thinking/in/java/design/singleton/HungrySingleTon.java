package org.codeme.thinking.in.java.design.singleton;

/**
 * 饿汉模式
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class HungrySingleTon {
    private static HungrySingleTon instance = new HungrySingleTon();

    private int count = 0;

    public void increment() {
        count++;
    }

    private HungrySingleTon() {
        System.out.println("HungrySingleTon ctor ...");
    }

    public static HungrySingleTon getInstance() {
        return instance;
    }

    public int getCount() {
        return count;
    }
}
