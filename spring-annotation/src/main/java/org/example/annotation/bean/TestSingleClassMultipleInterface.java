package org.example.annotation.bean;

import org.springframework.stereotype.Component;

/**
 * <p>创建时间: 2021/1/31 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Component
public class TestSingleClassMultipleInterface implements OneInterface, TwoInterface {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
}
