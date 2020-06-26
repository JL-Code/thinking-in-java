package org.example.design.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/25 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class SingletonTests {

    @Test
    @DisplayName("单线程下懒汉单例模式")
    void getInstance() {
        int counter = 50;
        int actual = 0;
        for (int i = 0; i < counter; i++) {
//            Thread thread1 = new Thread(new Runnable() {
//                public void run() {
//                    Singleton.getInstance();
//                }
//            });
//            Thread thread2 = new Thread(new Runnable() {
//                public void run() {
            Singleton.getInstance();
//                }
//            });
//            thread1.start();
//            thread2.start();
        }

        actual = Singleton.getInstance().getCounter();
        System.out.println("counter:" + counter);
        System.out.println("actual:" + actual);
        Assertions.assertEquals(counter + 1, actual);

    }

    @Test
    @DisplayName("单例模式多线程测试")
    void testMultithreading() throws InterruptedException {
        Runnable task = new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("counter：" + Singleton.getInstance().getCounter());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        startTaskAllInOnce(5, task);
    }


    public long startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for (int i = 0; i < threadNums; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            // 将结束门减1，减到0时，就可以开启结束门了
                            endGate.countDown();
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        long endTime = System.nanoTime();
        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
        return endTime - startTime;
    }
}