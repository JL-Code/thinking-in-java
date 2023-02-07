package org.example.basic.thread;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.nio.ByteOrder;

/**
 * 查看 Java 对象头布局示例：
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @ref https://www.cnblogs.com/hongdada/p/14087177.html JAVA 对象头分析及Synchronized锁
 * <p>创建时间: 2023/2/4 </p>
 * @since
 */
public class ObjectHeadDemo {
    public static void main(String[] args) {
        //查看字节序
        System.out.println(ByteOrder.nativeOrder());
        //打印当前jvm信息
        System.out.println("======================================");
//        System.out.println(VM.current().details());

        // 查看分析基本类型对象布局
//        System.out.println(ClassLayout.parseClass(String.class).toPrintable());
        String o = "hello word";
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
