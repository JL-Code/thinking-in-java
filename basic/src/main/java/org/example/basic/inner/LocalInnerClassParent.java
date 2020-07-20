package org.example.basic.inner;

public class LocalInnerClassParent {
    public Object build(boolean inner) {
        if (inner) {
            /**
             * 1. 局部内部类**访问父类成员无限制**。
             * 2. 局部内部类**没有修饰符**。
             */
            class LocalInnerClass {
                public LocalInnerClass() {} // 显式声明无参构造函数
                private int id;
                private String name;
                private String localInnerClassName;

                public LocalInnerClass(int id, String name, String localInnerClassName) {
                    this.id = id;
                    this.name = name;
                    this.localInnerClassName = localInnerClassName;
                }
            }

            return new LocalInnerClass(1, "jiangy", "LocalInnerClass");
        }
        return null;
    }
}
