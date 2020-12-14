package org.example.design.decorator;

/**
 * 装饰器调用客户端
 * 装饰器组装逻辑，暴露在客户端。
 */
public class Client {
    public static void main(String[] args) {

        Notifier notifier = new DefaultNotifier();
        notifier = new QQDecorator(notifier);
        notifier = new SMSDecorator(notifier);

        /**
         * 方法栈
         * |SMSDecorator|
         * |QQDecorator|
         * |DefaultNotifier|
         */
        notifier.send("装饰器消息发送。");
    }
}
