package org.example.unittest.service;

import org.example.unittest.dao.UserDao;

/**
 * <p>创建时间: 2021/4/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean save(User user) {
        String username = getUsername();
        boolean result = userDao.insert(username);
        return result;
    }

    @Override
    public String getUsername() {
        return "username";
    }
}
