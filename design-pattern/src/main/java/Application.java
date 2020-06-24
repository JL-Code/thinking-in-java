import 泛型.PointByGeneric;
import 泛型.Message;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/23 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Application {

    public static void main(String[] args) {

        // Object 方式
//        PointByObject point = new PointByObject();
//        point.setX("12");
//        point.setY("12");
//
//        int x = (Integer) point.getX();
//        int y = (Integer) point.getY();

        // Generic 方式
//        PointByGeneric point = new PointByGeneric();
//        point.setX(12);
//        point.setY(12);
//
//        int x = (Integer) point.getX();
//        int y = (Integer) point.getY();
//
//        System.out.println("x:" + x + "y:" + y);

        // 泛型引用传递问题，在引用传递时必须显式指明具体的泛型类型，否则只能使用 Object类型，这样就失去了泛型的灵活性（运行时指定类型）
        Message<Integer> message = new Message<Integer>();
        message.setContent(1212);
        func1(message);
    }
    public static void func1(Message message) {
        message.setContent("121222");
        System.out.println(message.getContent());
    }
    public static void func(Message<String> message) {
        //
        message.setContent("1212");
        System.out.println(message.getContent());
    }



}
