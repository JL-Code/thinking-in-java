## ThreadLocal 详解

## Java GC 回收机制



## Java 强软弱虚引用

### 强引用 StrongReference



`Object obj = new Object()` 

### 软引用 SoftReference

> 当 JVM 内存不足时，会释放软引用，软引用适合做缓存。

软引用的内存布局：

![软引用内存布局](D:\Workspace\java-example\doc\assets\软引用内存布局.png)

测试代码：

**有个问题：当 hardBytes的大小超过 12M 左右时，会抛出 OOM，看来 软引用的回收并没有达到 100%的回收。**

```java
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

```



### 弱引用 WeakReference

> 弱引用

### 虚引用 PhantomReference

## ThreadLocal

### UML 类图

<img src="D:\Workspace\java-example\doc\assets\ThreadLocal类图" alt="image-20200725235024898" style="zoom:50%;" />

```java
ThraedLocal<StringBuilder> tl = new TreadLocal<>();
// 将 tl 做为 key，new StringBuilder() 做为 value 存放到 currentThread 中的 ThreadLocalMap 中。 
tl.set(new StringBuilder()); 
```

### 内存布局模型

![Thread-ThreadLocal-内存布局关系](D:\Workspace\java-example\doc\assets\Thread-ThreadLocal-内存布局关系.png)

### 内存泄漏隐患

### 案例

#### @Transactional

### 总结

1. Java 中引用类型有哪几种？
2. 每种应用类型的特点是什么？
3. 每种引用类型的应用场景是什么？
4. ThreadLocal 你了解吗？
5. ThreadLocal 应用在什么地方？
6. ThreadLocal 会产生内存泄漏你了解吗？

## 参考文档

* [理解Java的强引用、软引用、弱引用和虚引用](https://juejin.im/post/5b82c02df265da436152f5ad#heading-0)

