package org.example.basic.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程可见性示例：
 * <p>创建时间: 2023/2/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class MultithreadVisibilityDemo {

    public static int counter = 0;
    public static AtomicInteger counter2 = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter++;
                counter2.getAndIncrement();
            }
            System.out.println("线程1结束");
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter++;
                counter2.getAndIncrement();
            }
            System.out.println("线程2结束");
        }).start();

        // 等待子线程结束
        Thread.sleep(1000);

        System.out.println(counter);
        System.out.println(counter2);
    }
}
