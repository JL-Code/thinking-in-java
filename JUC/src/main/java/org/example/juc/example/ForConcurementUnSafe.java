package org.example.juc.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForConcurementUnSafe {

    private int count;

    public void run() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 设置 CountDownLatch 的计数器为 100，保证在主线程打印累加结果之前，100 个线程已经执行完累加
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                increment();
                // 每一个线程执行完累加操作，都将计数器减 1
                latch.countDown();
            });
        }
        // 主线程等待，直到 cdl 的计数器为0
        latch.await();
        System.out.println("计数器执行完100次累加后值为：" + getCount());
    }

    public void increment() {
        count++;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
