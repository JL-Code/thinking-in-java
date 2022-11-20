# Java 内部类
> 在一个类的内部以某种形式（成员、静态成员、局部作用域）定义另一个类，我们将这种“另一个类”称为“一个类”的内部类。内部类可以访问外部类中任意成员（包括 private 修饰的成员）。

🤔 思考问题：
1. 如何实例化内部类对象？
2. 内部类与外部类成员重名时如何访问？
## 1. 应用场景
1. 多继承实现

## 2. 成员内部类

#### 定义
> 内部类作为外部类的一个成员。

#### 特性
1. 成员内部类无法拥有 `static` 修饰的成员和方法。
2. 成员内部类作为外部类的成员，所有实例内部类前，必须先实例外部类。

#### 代码示例
> 成员内部类
```java
public class Outer {
    private String name;  // 重名成员  
    private String outerField;

    public void outerMethodName() {
        System.out.println("我是外部类方法");
    }

    class Inner {
        private String name; // 重名成员
        private String innerField;

        public void innerMethod(String name) { // 重名成员
            System.out.println("我是内部类方法");
            // 内部类中访问方法形参、自身类成员、外部类成员
            System.out.println(name); // 方法形参可直接访问。
            System.out.println(this.name); // 自身成员通过 this.属性名访问。
            // Outer.this 表示 Outer 的实例
            System.out.println(Outer.this.name); // 外部类成员通过 OuterClassName.this 获取到外部类的实例，然后通过 实例.属性名访问。
        }
    }
}
// =============测试代码=============
class OuterInnerTest {
    void test() {
        Outer outer = new Outer();
        // 通过 outer 实例创建内部类实例
        Outer.Inner inner = outer.new Inner();
        inner.innerMethod();
    }
}


```
👆上面代码编译后得到两个独立的字节码文件：
```text
Outer.class 
Outer$Inner.class
```

## 3. 局部内部类

> 定义在方法内部或作用域中的内部类。

#### 特性

1. 局部内部类**访问父类成员无限制**。
2. 局部内部类**没有修饰符**。

#### 代码示例
```java
public class LocalInnerClassParent {
    public LocalInnerClass() {} // 显式声明无参构造函数
    public Object build(boolean inner) {
        if (inner) {
            // 定义在局部作用域的类，称为局部内部类。
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
// 测试代码
@Test
public void testLocalInnerClassParent() {
    LocalInnerClassParent parent = new LocalInnerClassParent();
    Object build = parent.build(true);

    Assertions.assertNotNull(build);
}
```

![image-20200720224605419](D:\Workspace\thinking-in-java\doc\assets\image-20200720224605419.png)

> 提示：this$0 是匿名内部类隐式持有的一个外部类对象，`LocalInnerClassParent@1599` 。 

## 4. 静态内部类 

> 以 `static` 修饰的成员内部类。

#### 特性
1. 静态类的实例化不需要实例化外部类，可以直接 `new 外部类.内部类()`。
2. 静态内部类只能访问外部类 `static` 修饰的成员或方法。

#### 代码示例
```java
class Outer{
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
}
// =============测试代码=============
class OuterInnerTest {
    void test() {
        // 非静态匿名类实例化
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.innerMethod();

        // 静态匿名类实例化
        // 额外的知识点：C# 中静态类是无法实例化的（并且所有的成员必须也是静态的）。
        Outer.StaticInner staticInner = new Outer.StaticInner();
        staticInner.staticInnerMethodName();
    }
}
```

## 5. 匿名内部类

#### 特性
1. 方法形参必须使用 `final` 修饰（**JDK 1.7+** 编译器自动加上 `final`）。
2. 匿名内部类**没有修饰符** 。
3. 匿名内部类**没有构造函数**。
4. 匿名内部类是一次性的（类的定义不能重用）。
5. 匿名内部类不能是抽象的，它必须要实现继承的类或者实现的接口的所有抽象方法。
6. 匿名内部类**属于局部内部类**，局部内部类拥有的限制，匿名内部类同样拥有。


#### 语法
```text
new 接口[父类](){
    // 类主体内容
}
```

#### 代码示例
```java
// 地址接口
public interface IAddress {
    public String getProvince();
    public String getCity();
    public String getCounty();
}
// =============测试代码=============
class AnonymityClassTest{
    private String propName;
    void test(){
        // 实例化一个地址匿名类
        IAddress address = new IAddress() {
            public String getProvince() {
                return "重庆";
            }
            public String getCity() {
                return "重庆";
            }
            public String getCounty() {
                return "南岸区";
            }
        };
        System.out.println(address.getCity());    
    }
}
```


## 参考文档
- [java内部类有什么作用？](https://www.zhihu.com/question/26954130/answer/708467570)