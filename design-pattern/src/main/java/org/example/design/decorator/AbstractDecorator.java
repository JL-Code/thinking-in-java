package org.example.design.decorator;

/**
 * 抽象装饰者
 */
public abstract class AbstractDecorator implements Notifier {


    private Notifier decoreatee;

    public AbstractDecorator(Notifier notifier) {
        this.decoreatee = notifier;
    }

    @Override
    public Notifier getDecoreatee() {
        return decoreatee;
    }

    @Override
    public void setDecoreatee(Notifier decoreatee) {
        this.decoreatee = decoreatee;
    }

    public void send(String message) {
        // 额外的工作
        // 调用被装饰者发送消息
        this.decoreatee.send(message);
    }

    /**
     * 装饰器额外的行为
     */
    public void extraAction() {

    }

}
