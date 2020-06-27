package org.example.design.singleton;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class LazySingleTonByStaticInnerClass {


    private LazySingleTonByStaticInnerClass() {
        System.out.println("LazySingleTonByStaticInnerClass ctor ...");
    }

    public static LazySingleTonByStaticInnerClass getInstance() {
        return LazySingleTonByStaticInnerClassHolder.instance;
    }

    public static void getTest() {
        System.out.println("查看静态内部类是否已经实例化了...");
    }

    private static class LazySingleTonByStaticInnerClassHolder {
        private final static LazySingleTonByStaticInnerClass instance = new LazySingleTonByStaticInnerClass();
    }

}