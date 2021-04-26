package org.example.unittest.dao;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * <p>创建时间: 2021/4/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Repository
public class UserDao {
    public boolean insert() {
        System.out.println("user insert success");
        return true;
    }
}
