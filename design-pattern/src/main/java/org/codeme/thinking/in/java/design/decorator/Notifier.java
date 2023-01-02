package org.codeme.thinking.in.java.design.decorator;

/**
 * 通知器(被装饰者)
 * 装饰者模式的示例代码
 * 装饰者模式：
 * 本质：动态的为对象增加额外行为。
 * 现状：为对象增加额外的行为，可以通过继承来实现。但是继承存在以下两点缺点：
 * 1. 继承是静态的行为，无法在运行时为对象增加额外的行为，只能使用由不同子类创建的对象来替代当前的整个对象。
 * 2. 大多数 oop 语言都仅支持单继承，一个子类无法同时拥有多个父类。
 * 方式：
 * 1.装饰器实现了与其装饰对象相同的接口 【因此从客户端的角度来看， 这些对象是完全一样的。 封装器中的引用成员变量可以是遵循相同接口的任意对象。 这使得你可以将一个对象放入多个封装器中， 并在对象中添加所有这些封装器的组合行为】
 */
public interface Notifier {
    void send(String message);

    Notifier getDecoreatee();

    void setDecoreatee(Notifier decoreatee);
}
