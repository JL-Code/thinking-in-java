# [📒详细版本参考语雀笔记](https://www.yuque.com/mecode-7pn28/vgeyn0/whrz8d)

## 1. 泛型解决的问题

> 解决算法重用问题，让算法适用于更多的数据类型。

在没有泛型出现的时期，程序员们普遍采取 `Object` 类型作为不确定类型参数的类型，
这虽然解决了编码的问题，但是也带来了**类型强制转换问题从而带了类型安全问题（向下转换类型存在 `ClassCastException` 的情况）**。

`String` 转为 `Integer` 就可能会发生 `ClassCastException` 异常。

`Message<T>` ：

```java
public class Message<T> {
  T content;
  public T getContent() {
    return content;
  }
  public void setContent(T content) {
    this.content = content;
  }
  public void func() {}
}
```

* 编译器类型检查
  * 让类型检查尽可能的编译时期就发现。
  `Message<String> message = new Message<>();
   message.setContent(111);` 将编译不通过,`Integer` 与 `String` 不匹配。  
* 强制类型转换
  * 使用泛型类型将类型以参数的形式传递。`Message<String> message = new Message<>()` 这样编译器就知道了 `Message` 中 `content` 的类型为 `String` 。

## 2. 泛型的定义 （Generic）

`JDK 1.5+` `Java` 提供了泛型来解决类型强制转换所带来的类型安全问题。

泛型的本质在于对象可以在运行时决定类型。

在类定义时使用占位符来占位，当对象运行时再来决定具体的类型。

为了兼容 `JDK 1.5-` 的代码，使用 `Object` 作为默认泛型类型。

### 2.1. 泛型类

### 2.2. 泛型方法

## 3. 泛型的新问题

引用传递参数限制

```java
class App {
    public static void main(String[] args) {
        Message<Integer> message = new Message<Integer>();
        message.setContent(1212);
        // 引用传递 func 方法的参数类型必须指定具体泛型类型
        func(message);
    }
    // func 的参数应该是可以接受任意类型的泛型参数。但是现实是 func 的类型参数只能指定具体泛型类型 eg：Message<Integer>。
    public static void func(Message<Integer> message) {
        message.setContent(121222);
        System.out.println(message.getContent());
    }
}
```

泛型解决了类型限制的问题，但是带了引用类型参数传递问题（引用类型参数的类型不通用，不能同时满足 `Message<String>` 、 `Message<Integer>` ...）。
为了解决引用类型参数传递问题有一种方案：

1. 将 `func` 的参数类型设置为 `Object`,此时 `func` 方法可以接受任意的类型参数。

但是这个方案有两个问题：

1. 不设置泛型，将参数类型改为 `Object`，此时又回到了类型强制转换的安全问题。

2. 参数设置为 `Object` 时，`func` 方法内的代码则可以对参数进行二次加工（更改数据）。

为了解决上述两个问题，Java 带来了 `通配符` 。
> `Message<?>` 类型中 `?` 为无界通配符，`<? extends T>` 表示上界通配符，`<? super T>` 表示下界通配符。

```java
class App{
     public static void func(Message<?> message) {
// 当设置了通配符<?>后则无法修改数据
// message.setContent(121222);
            System.out.println(message.getContent());
        }
}
```

此时的 `func(Message<?> message)` 方法可以接受任意类型的泛型参数。并且由于 “?” 无界通配符表示任意类型，所以 `func` 方法内的代码在编译时操作数据。

## 4. 通配符

> ref: https://blog.csdn.net/justry_deng/article/details/82632090

### 4.1 `<? super T>`  下界通配符

`<? super T>` 之所以叫下界通配符，是因为 `?` 是所有 `T` 的父类，即到最下面就是 `T` 了。

> 我们一般可以往 `<? super T>` 中设置 `?` 的实例但是不获取值， 因为获得的对象只能以 `Object` 来接收,因为不知道
> 获取的到底是 `T` 的哪一个父类，当使用 `Object` 来接时，会损失部分该对象的功能。

应用场景

### 4.2 `<? extends T>`  上届界配符

`<? extends T>` 之所以叫上界通配符，是因为 `?` 是所有 `T` 的子类，即到最上面就是 `T` 了。

### 4.3. 泛型占位符 T 与 通配符 ? 的区别

用途：

* `T` 用于声明泛型类型。
eg： `T content` 声明了变量 `content` 的类型是 `T`。
* `?` 用于使用泛型。
eg: `doSomething(Message<?> message)` 可以让 `doSomething` 能接受任意类型的泛型参数（避免了类型强制转换），并且方法内部无法修改 `message` 保证了数据安全。

### 4.4 小结

```java
<? extends T> 等价于 ? extends T
<? super T> 等价于 T extends ?
```

* `<? extends T>` 是将左侧泛型的子类 `?` (或其本身)赋给左侧的泛型引用;
* `<? super T>` 则是将左侧泛型的父类 `?` (或其本身)赋给左侧的泛型引用。

> https://stackoverflow.com/questions/1368166/what-is-a-difference-between-super-e-and-extends-e

## 5. 泛型的注意点

 1. Java 的泛型只支持引用类型，不支持原生类型。
 2. JDK 1.7 后语法有更改，Point<Integer,Integer> point = new Point<>(); 其中 “=” 右边的表达式不需要再显示指定泛型类型。
