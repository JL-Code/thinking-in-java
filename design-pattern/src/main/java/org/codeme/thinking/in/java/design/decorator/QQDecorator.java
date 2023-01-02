package org.codeme.thinking.in.java.design.decorator;

public class QQDecorator extends AbstractDecorator {
    public QQDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        try {
            System.out.println("qq decorator 发送了QQ消息：" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.send(message);
    }
}
