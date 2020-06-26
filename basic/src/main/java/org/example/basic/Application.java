package org.example.basic;

import org.example.basic.泛型.Message;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/23 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Application {

    public static void main(String[] args) {
        Message<Integer>[] messages = new Message[10];
        System.out.println(messages.length);
    }
}
