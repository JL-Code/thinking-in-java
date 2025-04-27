package org.example.basic.thread;

public class ThreadCreationMethodDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");
        });

        t1.start();

        Thread.sleep(300);

        int cpus = Runtime.getRuntime().availableProcessors();
        System.out.println("可用 CPU 核心数 : " + cpus);

        System.out.println("main thread end");

    }
}
