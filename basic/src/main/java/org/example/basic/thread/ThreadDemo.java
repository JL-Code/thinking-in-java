package org.example.basic.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程同步示例： 演示交替打印数字 1-10.
 * 思路：创建一把对象锁及两个线程，让两个线程循环10次交替获取对象锁，拿到锁的线程执行一次，
 * 执行完后立即释放锁，由另一个线程获取并进入到执行状态执行完成后立即释放锁
 * <p>创建时间: 2023/2/2 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                for (int i = 1; i <= 10; i++) {
                    if (i % 2 != 0) {
                        System.out.printf("%s %d \n", Thread.currentThread().getName(), i);
                        try {
                            o.notify();
                            System.out.println("t1 notify...");
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("t1 end");
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (o) {
                for (int i = 1; i <= 10; i++) {
                    if (i % 2 == 0) {
                        System.out.printf("%s %d \n", Thread.currentThread().getName(), i);
                        try {
                            o.notify();
                            System.out.println("t2 notify...");
                            if (i != 10) {
                                o.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("t2 end");
        }, "t2");

        t1.start();
        // 让主线程休息100ms，确保 t1 先拿到锁。
        Thread.sleep(100);
        t2.start();

    }
}
