package org.example.annotation.dao;

import org.springframework.stereotype.Repository;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Repository
public class HelloDao {

    public HelloDao() {
        this.label = "label1";
    }

    public HelloDao(String label) {
        this.label = label;
    }

    private String label;

    @Override
    public String toString() {
        return "HelloDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
