package org.example.basic;

import java.lang.ref.SoftReference;

/**
 * 软引用测试
 * 测试时 JVM 内存分配设置 20M https://www.cnblogs.com/jack204/archive/2012/07/02/2572932.html
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        // 声明一个软引用字节数组
        SoftReference<byte[]> sfBytes = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(sfBytes.get());
        // 手动 GC
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sfBytes.get());

        // 再分配一个数组，heap 装不下时，这时系统会进行垃圾回收一次，如果回收后内存还是不够，会把软引用占用的内存释放掉。
        // 有个问题：当 hardBytes的大小超过 12M 左右时，会抛出 OOM，看来 软引用的回收并没有达到 100%的回收。
        byte[] hardBytes = new byte[1024 * 1024 * 10];
        System.out.println(sfBytes.get());
    }
}
