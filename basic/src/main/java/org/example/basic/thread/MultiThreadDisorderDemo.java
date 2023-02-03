package org.example.basic.thread;

import java.util.concurrent.Semaphore;

/**
 * 多线程指令重排示例：
 * 思路：
 * 1.创建一个 writer 方法按照 a=1 flag=true 的顺序给变量赋值
 * 2.创建一个 reader 方法依次判断 flag = true && a==0 则输出 “发生了指令重排，此时 a 的值”
 * 3.创建一个死循环在里面创建两个线程依次交替执行 reader、writer 方法，如果输出了 “发生了指令重排” ，则证明发生了指令重排，因为
 * 按照代码编写顺序 reader执行后 a=1，flag=true 在 writer 方法里面  a==0 && flag==true 就不会成立。
 * <p>创建时间: 2023/2/3 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public class MultiThreadDisorderDemo {
    int a = 0;
    boolean flag = false;

    public void writer() {
        a = 1;
        flag = true;
    }

    public void reader() {
        if (flag) {
            System.out.println("执行中");
            if (a == 0) {
                System.out.printf("发生了指令重排：此时 a=%d", a);
            }
        }
    }

    public static void main(String[] args) {
        // 声明 100个窗口,这里声明默认的是非公平锁，防止资源耗尽
        Semaphore windows = new Semaphore(100);
        for (; ; ) {
            MultiThreadDisorderDemo disorderTest = new MultiThreadDisorderDemo();
            new Thread(() -> {
                try {
                    windows.acquire();// 占用窗口
                    disorderTest.reader();
                    windows.release();// 释放窗口，这里释放以后，第后面的线程才能继续获取
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    windows.acquire();// 占用窗口
                    disorderTest.writer();
                    windows.release();// 释放窗口
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
