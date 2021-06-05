package org.example.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.mybatis.model.User;
import org.example.mybatis.repository.UserRepository;
import org.example.mybatis.service.UserService;

import java.util.List;

/**
 * <p>创建时间: 2021/4/22 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class UserServiceImpl2 implements UserService {

    private final UserRepository repository;

    public UserServiceImpl2(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public IPage<User> listUser(long page, long size, String agentId) {
        Page<User> pagination = new Page<>(page, size);
        // unit test dependency 1
        List<User> users = repository.selectUserPage(pagination, agentId);
        pagination.setRecords(users);
        // unit test dependency 2
        unitTest();
        return pagination;
    }

    @Override
    public void unitTest() {
        System.out.println("单元测试 unitTest");
    }
}
