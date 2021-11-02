package org.example.unittest.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/11/1 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class MapConcurrentUnSafeCaseTest {

    @Test
    void run() throws InterruptedException {
        MapConcurrentUnSafeCase unSafeCase = new MapConcurrentUnSafeCase();

        for (int i = 0; i < 50; i++) {
            unSafeCase.run();
        }
    }

    @Test
    void testMap() {
        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            Map<String, UUID> map = new HashMap<>();
            // TODO: 如何捕获多线程抛出的异常？
            for (int i = 0; i < 30; i++) {
                new Thread(() -> {
                    map.put(Thread.currentThread().getName(), UUID.randomUUID());
                    System.out.println(map);
                }, String.valueOf(i)).start();
            }
        });
    }
}