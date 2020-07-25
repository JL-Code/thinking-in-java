package org.example.basic.thread;

import java.util.concurrent.CountDownLatch;

/**
 * <p>创建时间: 2020/7/22 </p>
 * <p>
 * ref: Java进阶（七）正确理解Thread Local的原理与适用场景
 * http://www.jasongj.com/java/threadlocal/
 * ref: Java并发编程：CountDownLatch、CyclicBarrier和Semaphore
 * https://www.cnblogs.com/dolphin0520/p/3920397.html
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class ThreadLocalTest {


    public static void main(String[] args) throws InterruptedException {

        int threads = 3;
        // 实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        InnerClass innerClass = new InnerClass();
        for (int i = 1; i <= threads; i++) {
            new Thread(() -> {
                for (int j = 0; j < 4; j++) {
                    innerClass.add(String.valueOf(j));
                    innerClass.print();
                }
                innerClass.set("hello world");
                countDownLatch.countDown();
            }, "thread - " + i).start();
        }
        countDownLatch.await();

    }

    private static class InnerClass {

        public void add(String newStr) {
            // 返回此线程局部变量的当前线程副本中的值。
            StringBuilder str = Counter.counter.get();
            Counter.counter.set(str.append(newStr));
        }

        public void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }

        public void set(String words) {
            Counter.counter.set(new StringBuilder(words));
            System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }
    }

    private static class Counter {

        private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };

    }

}
