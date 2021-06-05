package org.example.springboot.cases.transational.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.springboot.cases.transational.user.User;
import org.springframework.stereotype.Repository;

/**
 * 异常：Invalid bound statement (not found):
 */
@Repository
public interface UserDao extends BaseMapper<User> {
}
