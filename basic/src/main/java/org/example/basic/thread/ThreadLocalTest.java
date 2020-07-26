package org.example.basic.thread;

import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

/**
 * <p>创建时间: 2020/7/22 </p>
 * <p>
 * ref: Java进阶（七）正确理解Thread Local的原理与适用场景
 * http://www.jasongj.com/java/threadlocal/
 * ref: Java并发编程：CountDownLatch、CyclicBarrier和Semaphore
 * https://www.cnblogs.com/dolphin0520/p/3920397.html
 * ThreadLocal 学习视频：https://www.bilibili.com/video/BV1SD4y1D7r2?from=search&seid=18291778357999069619
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class ThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<StringBuilder> stringBuilderThreadLocal = new ThreadLocal<>();
        stringBuilderThreadLocal.set(new StringBuilder());

        stringBuilderThreadLocal.get().append("1212");

        stringBuilderThreadLocal.remove();
    }

}
