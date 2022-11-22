package org.codeme.thinking.in.java.thread.oversold;

/**
 * 多线程超卖示例
 * <p>创建时间: 2022/11/22 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class MultiThreadOversoldDemo {
    public static void main(String[] args) {

        TicketSellingService sellingService = new TicketSellingService();

        Thread thread1 = new Thread(sellingService);
        Thread thread2 = new Thread(sellingService);
        Thread thread3 = new Thread(sellingService);
        Thread thread4 = new Thread(sellingService);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
