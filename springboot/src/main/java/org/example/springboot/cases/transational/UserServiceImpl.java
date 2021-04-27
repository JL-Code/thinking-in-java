package org.example.springboot.cases.transational;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.springboot.cases.transational.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    /**
     * TODO: 这是一个糟糕的事务回滚案例，推荐采用支付订单案例
     * @param user
     * @return
     */
    @Transactional
    @Override
    public boolean addUser(User user) {
        int insert = this.baseMapper.insert(user);
        // 当 username = jiangy 时，触发一个异常
        if (user.getUsername().equals("jiangy")) {
            throw new RuntimeException("运行异常");
        }
        return insert > 0;
    }
}
