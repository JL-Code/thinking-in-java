package org.codeme.thinking.in.java.thread;

/**
 * 打印机
 * <p>创建时间: 2022/11/21 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class Printer implements Runnable {

    public Printer(int maxCount) {
        this.maxCount = maxCount;
    }

    public Printer(int maxCount, boolean isSynchronization) {
        this.maxCount = maxCount;
        this.isSynchronization = isSynchronization;
    }

    private int maxCount = 10;
    private boolean isSynchronization = false;

    private SynchronizationType synchronizationType;

    /**
     * 同步方法（方法增加 synchronized 修饰关键字）
     * 打印 1-100
     */
    public synchronized void print() throws InterruptedException {
        for (int i = 0; i < maxCount; i++) {
            System.out.printf("线程：%s i：%d \n", Thread.currentThread().getName(), i);
            Thread.sleep(500);
        }
    }

    public void print2() throws InterruptedException {
        for (int i = 0; i < maxCount; i++) {
            System.out.printf("线程：%s i：%d \n", Thread.currentThread().getName(), i);
            Thread.sleep(500);
        }
    }

    /**
     * synchroization block 同步代码块
     * synchronized(this){
     * for (int i = 0; i < maxCount; i++) {
     * System.out.printf("线程：%s i：%d \n", Thread.currentThread().getName(), i);
     * Thread.sleep(500);
     * }
     * }
     */
    public void print3() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < maxCount; i++) {
                System.out.printf("线程：%s i：%d \n", Thread.currentThread().getName(), i);
                Thread.sleep(500);
            }
        }
    }

    @Override
    public void run() {
        try {
            if (isSynchronization) {
                switch (synchronizationType) {
                    case SYNCHRONIZATION_METHOD:
                        print();
                        break;
                    case SYNCHRONIZATION_BLOCK:
                        print3();
                        break;
                    case STATIC_SYNCHRONIZATION:
                        InnerPrinter.print(maxCount);
                        break;
                }
            } else {
                print2();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置线程同步类型 {@link SynchronizationType}
     *
     * @param synchronizationType
     */
    public void setSynchronizationType(SynchronizationType synchronizationType) {
        this.synchronizationType = synchronizationType;
    }

    public static class InnerPrinter {
        /**
         * 静态同步
         *
         * @param maxCount 最大记录
         */
        public synchronized static void print(int maxCount) throws InterruptedException {
            for (int i = 0; i < maxCount; i++) {
                System.out.printf("线程：%s i：%d \n", Thread.currentThread().getName(), i);
                Thread.sleep(500);
            }
        }
    }

    /**
     * 同步类型
     */
    public enum SynchronizationType {
        /**
         * 同步方法
         */
        SYNCHRONIZATION_METHOD,
        /**
         * 同步代码块
         */
        SYNCHRONIZATION_BLOCK,
        /**
         * 静态方法同步
         */
        STATIC_SYNCHRONIZATION
    }
}
