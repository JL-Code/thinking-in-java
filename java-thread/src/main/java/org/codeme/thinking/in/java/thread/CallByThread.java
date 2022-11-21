package org.codeme.thinking.in.java.thread;

/**
 * 该类由线程调用 {@link Runnable}
 * <p>创建时间: 2022/11/21 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class CallByThread implements Runnable {

    /**
     * 该方法有线程调用
     */
    @Override
    public void run() {
        for (int i = 0; i < 120; i++) {
            System.out.printf("Thread Name: %s i: %d \n", Thread.currentThread().getName(), i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
