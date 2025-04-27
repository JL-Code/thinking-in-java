package org.example.basic.thread;

public class HappensBeforeJoinDemo {
    private static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread threadB = new Thread(() -> {
            System.out.printf("Thread B , value=%d \n", value);
            value = 42; // 线程B的操作
        });

        System.out.printf("Thread A, value=%d \n", value);

        // happens-before start() 规则：
        // 如果线程 A 执行操作 ThreadB.start()（启动线程 B），
        // 那么 A 线程的 ThreadB.start()操作 happens-before 于线程 B 中的任意操作。
        value = 32;
        threadB.start();

        //  happens-before join() 规则：
        //  如果线程 A 执行操作 ThreadB.join()并成功返回，
        //  那么线程 B 的任意操作 happens-before 于线程 A 从 ThreadB.join()操作成功返回。
        threadB.join(); // 线程A(主线程)等待threadB结束

        // 保证输出42
        System.out.printf("Thread A, value=%d \n", value);
    }
}
