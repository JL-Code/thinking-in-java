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
    @Override
    public boolean save() {
        boolean result = userDao.insert();
        return result;
    }
}
