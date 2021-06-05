package org.example.springboot.cases.transational.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.springboot.cases.transational.User;
import org.example.springboot.cases.transational.UserService;
import org.example.springboot.cases.transational.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {


    /**
     * TODO: 这是一个糟糕的事务回滚案例，推荐采用支付订单案例
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public boolean addUser(User user) {
        int insert = this.baseMapper.insert(user);
        return insert > 0;
    }

    @Override
    public boolean nestedCallTransactionMethod(User user) {
        boolean result = addUser(user);
        // 模拟抛出一个异常，期望 addUser 方法回滚。
        if (user.getUsername().equals("jiangy")) {
            throw new RuntimeException("运行异常");
        }
        return result;
    }

    @Autowired
    private PlatformTransactionManager txManager;

    @Override
    public User imperativeTransactionCase(User user) {
        // 1、创建事务定义
        TransactionDefinition definition = new DefaultTransactionDefinition();
        // 2、根据定义开启事务
        TransactionStatus status = txManager.getTransaction(definition);
        try {
            this.baseMapper.insert(user);
            // 3、提交事务
            txManager.commit(status);
            return this.baseMapper.selectById(user.getId());
        } catch (Exception ex) {
            // 4、异常了，回滚事务
            txManager.rollback(status);
            throw ex;
        }
    }

    @Override
    public boolean remove(String userId) {
        LambdaQueryWrapper<User> predicate = Wrappers.<User>lambdaQuery().eq(User::getId, userId);
        User user = super.baseMapper.selectOne(predicate);
//        User user = super.baseMapper.selectById(userId);
        return user == null;
    }
}
