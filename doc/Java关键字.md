# Java 关键字
## final

## static

## instanceof

　instanceof 严格来说是Java中的一个双目运算符，用来测试一个对象是否为一个类的实例，用法为：

`boolean result = obj instanceof Class` 其中 obj 为一个对象，Class 表示一个类或者一个接口，**当 obj 为 Class 的对象，或者是其直接或间接子类，或者是其接口的实现类，结果result 都返回 true，否则返回false。**

> 　注意：编译器会检查 obj 是否能转换成右边的class类型，如果不能转换则直接报错，如果不能确定类型，则通过编译，具体看运行时定。

## 参考文档

* [浅析Java中的final关键字](https://www.cnblogs.com/dolphin0520/p/3736238.html)
* [深入浅出Java final关键字](https://zhuanlan.zhihu.com/p/33083924)