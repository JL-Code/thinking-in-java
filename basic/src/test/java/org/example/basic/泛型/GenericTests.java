package org.example.basic.泛型;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/25 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */

public class GenericTests {


    @Test
    public void test() {

        // Object 方式
        PointByObject point = new PointByObject();
        point.setX("12");
        point.setY("12");

//        int x = (Integer) point.getX();
//        int y = (Integer) point.getY();

        // Generic 方式
//        PointByGeneric point = new PointByGeneric();
//        point.setX(12);
//        point.setY(12);

        int x = (Integer) point.getX();
        int y = (Integer) point.getY();

        System.out.println("x:" + x + "y:" + y);
    }

    @Test
    @DisplayName("testGeneric")
    public void testGeneric() {
        // 泛型引用传递问题，在引用传递时必须显式指明具体的泛型类型，否则只能使用 Object类型，这样就失去了泛型的灵活性（运行时指定类型）
        Message<Integer> message = new Message<Integer>();
        message.setContent(100);
        // 引用传递
        Object obj = func1(message);
        Assertions.assertEquals("100", obj.toString());
    }


    Object func(Message message) {
        message.setContent("121222");
        return message.getContent();
    }

    Object func1(Message<?> message) {
        return message.getContent();
    }

    /**
     * 泛型方法
     *
     * @param message
     * @param <T>
     * @return
     */
    <T> T func2(Message<T> message) {
        return message.getContent();
    }
}
