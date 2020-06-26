package org.example.basic.泛型;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/23 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class Message<T> {


    T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void func() {

    }

}
