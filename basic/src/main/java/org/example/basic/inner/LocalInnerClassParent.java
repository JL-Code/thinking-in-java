package org.example.basic.inner;

public class LocalInnerClassParent {
    public Object build(boolean inner) {
        if (inner) {
            class LocalInnerClass {
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
