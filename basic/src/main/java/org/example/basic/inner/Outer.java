package org.example.basic.inner;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Outer {
    private String name;
    private String outerField;
    private static String staticField;

    public void outerMethodName() {
        System.out.println("我是外部类方法");
    }

    // 静态匿名类
    static class StaticInner {
        private String staticInnerPropName;
        private static String staticField;

        public void staticInnerMethodName() {
            System.out.println("staticInner staticInnerPropName：" + this.staticInnerPropName);
            System.out.println("outer staticField：" + Outer.staticField);
        }
    }

    class Inner {
        private String innerField;
        private String propName;
        private String name;

        public void innerMethod(String name) {
            // 内部类中访问方法形参、自身类成员、外部类成员
            System.out.println(name); // 方法形参可直接访问。
            System.out.println(this.name); // 自身成员通过 this.属性名访问。
            System.out.println(Outer.this.name);          // Outer.this 表示 Outer 的实例。外部类成员通过
        }
    }
}

class OuterInnerTest {
    void test() {
        // 非静态匿名类实例化
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.innerMethod("name");

        // 静态匿名类实例化
        // 额外的知识点：C# 中静态类是无法实例化的（并且所有的成员必须也是静态的）。
        Outer.StaticInner staticInner = new Outer.StaticInner();
        staticInner.staticInnerMethodName();
    }
}
