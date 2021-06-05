package org.example.springboot.validation;

/**
 * <p>创建时间: 2021/6/3 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public enum SexEnum {

    SEX_MEN("男", 1),
    SEX_WOMEN("女", 0),
    SEX_UNKNOWN("保密", 2);

    SexEnum(String name, int value) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    private String name;
    private int value;

}
