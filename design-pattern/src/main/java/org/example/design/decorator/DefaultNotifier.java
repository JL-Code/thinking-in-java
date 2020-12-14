package org.example.design.decorator;

public class DefaultNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("DefaultNotifier:" + message);
    }
}
