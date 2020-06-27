package org.example.basic.functional;


/**
 * <p>描述: [方法引用] </p>
 * <p>创建时间: 2020/6/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class MethodReference {

    public static void main(String[] args) {
        // 很奇怪 IAction 的方法签名虽然和String.valueOf 签名相同，但是一个有返回值一个没有返回值为什么表达式也能成立。
        IAction<String> action = String::valueOf;
        // lambda 表达式
        IAction<String> action2 = m -> {
            System.out.println("action2：" + m);
        };
        // 传统的面向对象结构化实现（先定义一个Action实现IAction接口）
        IAction<String> action3 = new Action<>();
        action3.execute("33");
        action2.execute("22");
        // String::valueOf 静态方法引用
        IFunc<Integer, String> func = String::valueOf;
        // 类型任意对象的实例方法引用（此种方法引用方式没有与具体的实例耦合），注：函数式接口的第一个参数必须是该类型对象，才能完成引用。
        IFunc3<String, Integer> func3 = String::compareTo;
        IFuncClassAnyObjectReference<Person, Integer> func33 = Person::compareToByName;
        // 特定对象("abc"字符串对象)的实例方法（toUpperCase 方法）引用，该方法引用与 “abc” 字符串对象严重耦合。
        IFunc2<String> func2 = "abc"::toUpperCase;

        // Person::new 构造方法引用
        IFunc<String, Person> func4 = Person::new;


        action.execute("action");
        String str = func.execute(100);
        String str2 = String.valueOf(100);
        System.out.println("func：" + str + "、length：" + str.length());
        System.out.println("toUpperCase：" + func2.execute());
        System.out.println("func3：" + func3.execute("22", "33"));
        System.out.println("func33：" + func33.execute(new Person("jiangy"), new Person("jiangy")));
        System.out.println("func4：" + func4.execute("jiangy"));

        Person person = new Person("jiangy");
        IFunc<String, String> func5 = person::hello;
        IFunc2 func6 = person::hello;

        func5.execute("对象引用");
        func6.execute();
    }

}

class Person {
    private String name;

    public String getName() {
        return name;
    }

    public Person(String name) {
        this.name = name;
    }

    public String hello(String name) {
        return "hello:" + name;
    }

    public String hello() {
        return "hello:" + name;
    }

    public int compareToByName(Person another) {
        return name.compareTo(another.getName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

interface IAction<T> {
    void execute(T t);
}

class Action<T> implements IAction<T>{
    @Override
    public void execute(T t) {
        System.out.println(t);
    }
}

interface IFunc<M, N> {
    N execute(M m);
}

interface IFunc2<M> {
    M execute();
}

interface IFunc3<M, N> {
    N execute(M m1, M m2);
}

interface IFuncClassAnyObjectReference<M, N> {
    N execute(M self, M m);
}