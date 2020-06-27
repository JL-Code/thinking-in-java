package org.example.basic.functional;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface IMessage {
    void send(String msg);
}

interface IMessage2 {
    void cleanMessage();
}

interface IMessage3 {
    String getMessage(String msg);
}

/**
 * @FunctionalInterface 显式声明接口为函数式接口
 */
@FunctionalInterface
interface IFunctionalMessage {
    void send(String message);
}


class MessageTests {
    public static void main(String[] args) {
//        IMessage message = new MessageImpl();
//        message.send("你好，世界");

        IFunctionalMessage functionalMessage = (msg) -> System.out.println(msg);

        IMessage functional = (msg) -> System.out.println(msg);
        IMessage2 functional2 = () -> System.out.println("functional2");
        IMessage3 functional3 = (msg) -> "hello：" + msg;

        functional.send("你好，Functional");
        functionalMessage.send("你好 FunctionalInterface");
    }
}

//class MessageImpl implements IMessage {
//
//    public void send(String msg) {
//        System.out.println(msg);
//    }
//}
