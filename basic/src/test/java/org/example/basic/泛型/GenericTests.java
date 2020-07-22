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
        Message<Integer> message = new Message<>();
        message.setContent(100);
        // 引用传递
        Object obj = func1(message);
        Integer obj2 = func2(message);

        Message<SMS> message2 = new Message<>();
        Message<SubSMS> subSMS = new Message<>();

        funcSMS(message2);
//     Java 泛型无法指定子类型替换占位的父类泛型  specificfuncSMS(subSMS);
        specificfuncSMS(message2);

        Assertions.assertEquals("100", obj.toString());
    }


    Object func(Message message) {
        message.setContent("121222");
        return message.getContent();
    }

    Object func1(Message<?> message) {
        return message.getContent();
    }

    SMS funcSMS(Message<? extends SMS> message) {
        SMS content = message.getContent();
        content.setType("sms change ...");
        return content;
    }

    SMS specificfuncSMS(Message<SMS> message) {
        SMS content = message.getContent();
        content.setType("sms change ...");
        return content;
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

    class SMS {
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type = "sms";
    }

    class SubSMS extends SMS {
        public String getType() {
            return type;
        }

        @Override
        public void setType(String type) {
            this.type = type;
        }

        private String type = "subsms";
    }

    /**
     * Determines if the class or interface represented by this Class object is either the same as, or is a superclass or superinterface of, the class or interface represented by the specified Class parameter. It returns true if so; otherwise it returns false. If this Class object represents a primitive type, this method returns true if the specified Class parameter is exactly this Class object; otherwise it returns false.
     * 确定由该类对象表示的类或接口是否与由指定的类参数表示的类或接口相同，或者是该类或接口的超类或超接口。如果是，则返回true；否则返回false。如果该类对象表示基元类型，则如果指定的类参数正是此类对象，则此方法返回true；否则返回false。
     * Specifically, this method tests whether the type represented by the specified Class parameter can be converted to the type represented by this Class object via an identity conversion or via a widening reference conversion. See The Java Language Specification, sections 5.1.1 and 5.1.4 , for details.
     * 具体地说，此方法测试指定的类参数所表示的类型是否可以通过标识转换或扩展引用转换转换转换为此类对象表示的类型。有关详细信息，请参阅Java语言规范第5.1.1和5.1.4节。
     */
    @Test
    public void testIsAssignableFrom() {
        // 判断 SMS 与 SubSMS 类型是否相同或 SMS 是 SubSMS 的超类或超接口。
        boolean flag1 = SMS.class.isAssignableFrom(SubSMS.class);
        boolean flag2 = SubSMS.class.isAssignableFrom(SMS.class);

        Assertions.assertTrue(flag1);
        Assertions.assertTrue(flag2);
    }
}
