package org.example.basic.keyword;

/**
 * TODO
 * <p>创建时间: 2022/11/29 </p>
 *
 * @author <a href="mailto:jiangliu0316@outlook.com" rel="nofollow">codeme</a>
 * @since
 */
public final class User implements IUser {
    private final String name = "mecode";
    private final int age;

    public User() {
        age = 0;
    }

    public String getName() {
        InnerClass inner = new InnerClass();
        inner.getName();
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int getSex() {
        return sex;
    }
}

class InnerClass {
    private String name;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


