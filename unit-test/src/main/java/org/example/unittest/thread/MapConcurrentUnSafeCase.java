package org.example.unittest.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * <p>创建时间: 2021/11/1 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class MapConcurrentUnSafeCase {

    private final Map<String, UUID> map = new HashMap<>();

    public void run() throws InterruptedException {
        int count = 30;
        CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                System.out.println("thread name:" + Thread.currentThread().getName());
                map.putIfAbsent(Thread.currentThread().getName(), UUID.randomUUID());
                latch.countDown();
            }, String.valueOf(i)).start();
        }

        latch.await();

        System.out.println("count:" + count);
        System.out.println("size:" + map.size());
        System.out.println(map);

        assert map.size() == count;

    }
}
