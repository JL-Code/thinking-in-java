package org.example.design.singleton;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class HungrySingleTon {
    private static HungrySingleTon instance = new HungrySingleTon();

    private HungrySingleTon() {
        System.out.println("HungrySingleTon ctor ...");
    }

    public static HungrySingleTon getInstance() {
        return instance;
    }
}
