package org.codeme.thinking.in.java.thread;

/**
 * TODO
 * <p>创建时间: 2022/11/20 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class JavaThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("CPU num: " + runtime.availableProcessors());

        Thread thread = new Thread(new CallByThread());
        thread.start();

        // 阻止主线程退出
//        Thread.sleep(1000 * 60);
    }
}
