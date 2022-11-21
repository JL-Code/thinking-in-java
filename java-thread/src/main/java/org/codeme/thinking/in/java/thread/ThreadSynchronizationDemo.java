package org.codeme.thinking.in.java.thread;

/**
 * 线程同步示例 {@link Runnable,Thread}
 * <p>创建时间: 2022/11/21 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class ThreadSynchronizationDemo {
    public static void main(String[] args) {

        // 1. 使用两个线程模拟两台电脑，同时使用打印机打印 1-100 。
        // 1.1 非线程同步
//        Printer printer = new Printer(100);
//        Thread thread1 = new Thread(printer);
//        Thread thread2 = new Thread(printer);
//
//        thread1.start();
//        thread2.start();
        // 1.2 线程同步

//        Printer syncPrinter = new Printer(100, true);
//        Thread syncP1 = new Thread(syncPrinter);
//        Thread syncP2 = new Thread(syncPrinter);
//
//        syncP1.start();
//        syncP2.start();

        Printer syncPrinter = new Printer(100, true);
        syncPrinter.setSynchronizationType(Printer.SynchronizationType.STATIC_SYNCHRONIZATION);

        Thread syncP1 = new Thread(syncPrinter);
        Thread syncP2 = new Thread(syncPrinter);

        syncP1.start();
        syncP2.start();
    }
}
