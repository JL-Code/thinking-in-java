package org.example.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mybatis.model.User;
import org.example.mybatis.repository.UserRepository;
import org.example.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>创建时间: 2021/2/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService {

    @Override
    public IPage<User> listUser(long page, long size, String agentId) {
        Page<User> pagination = new Page<>(page, size);
        List<User> users = this.baseMapper.selectUserPage(pagination, agentId);
//        this.baseMapper.selectList(Wrappers.<User>lambdaQuery().eq(User::getId, agentId));
        pagination.setRecords(users);
        return pagination;
    }
}
