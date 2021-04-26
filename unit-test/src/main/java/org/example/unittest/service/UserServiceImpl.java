package org.example.unittest.service;

import org.example.unittest.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>创建时间: 2021/4/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean saveUser() {
        return userDao.insert();
    }
}
