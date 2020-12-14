package org.example.design.decorator;

public class SMSDecorator extends AbstractDecorator {
    public SMSDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        // 子类装饰器做额外的操作
        System.out.println("sms decorator 发送了短信消息：" + message);
        super.send(message);
    }
}
