package org.example.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class JUCApplication {
    public static void main(String[] args) throws InterruptedException {

        int count = 300;
        final CountDownLatch latch = new CountDownLatch(count);
        Map<String, String> map = new HashMap<>();
        System.out.println("主线程执行...");
        // 开 <count> 个线程操作同时 map
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                // 线程结束后调用计时器自减
                latch.countDown();
            }, String.valueOf(i)).start();
        }
        latch.await();
        System.out.println(map.keySet());
        assert map.size() != count;
        System.out.println("主线程执行完毕...");
    }
}
