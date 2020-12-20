package org.example.design.decorator;

public class DefaultNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("DefaultNotifier:" + message);
    }

    @Override
    public Notifier getDecoreatee() {
        return null;
    }

    @Override
    public void setDecoreatee(Notifier decoreatee) {

    }
}
