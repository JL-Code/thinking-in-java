package org.example.basic.thread;

public final class ThreadLogger {

    public static void log(String message) {
        System.out.printf("time: %s threadId:%s %s \n", System.currentTimeMillis(), Thread.currentThread().getId(), message);
    }
}
