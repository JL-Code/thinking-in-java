package org.example.basic.thread;

import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 打印出如下线程信息
 * [1] main
 * [2] Reference Handler 引用处理线程
 * 处理与垃圾回收相关的特殊引用（软/弱/虚引用）
 * 将待处理的引用对象加入对应的引用队列
 * JVM参数：可通过-XX:+PrintReferenceGC观察其活动
 * [3] Finalizer 终结器线程
 * 调用实现了 finalize() 方法的对象的终结逻辑
 * 从Finalizer队列中取出对象执行finalize()
 * Java 9+已弃用finalize机制
 * [4] Signal Dispatcher 信号分发线程
 * 处理操作系统发送给JVM的信号
 * 例如处理Ctrl+C中断信号(SIGINT)
 * [12] Common-Cleaner 通用清理线程(Java 9+)
 * 替代Finalizer的现代清理机制
 * 执行Cleaner对象注册的清理操作
 * [13] Attach Listener 附加监听线程
 * 支持动态附加工具到JVM(如jstack, jmap)
 * 监听外部连接请求(默认不启动，首次attach时创建)
 * [14] Monitor Ctrl-Break 监控控制中断线程(IntelliJ IDEA特有)
 * IDE的调试监控线程
 * 处理IDE发送的调试控制命令
 * [15] Notification Thread 通知线程
 * 处理JMX(Java Management Extensions)通知
 * 将JMX MBean的通知事件分发给监听器
 */
public class PrintJavaProgramThreadInfoByJMX {
    public static void main(String[] args) {
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = java.lang.management.ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历打印线程信息(线程 id、线程名)
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }

}
