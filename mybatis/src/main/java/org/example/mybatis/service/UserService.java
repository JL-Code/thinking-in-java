package org.example.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.mybatis.model.User;

/**
 * <p>创建时间: 2021/2/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface UserService {
    IPage<User> listUser(long page, long size, String agentId);

    default void unitTest() {
        System.out.println("单元测试方法");
    }
}
