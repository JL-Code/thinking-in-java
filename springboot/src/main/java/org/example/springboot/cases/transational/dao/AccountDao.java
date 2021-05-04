package org.example.springboot.cases.transational.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.springboot.cases.transational.account.Account;
import org.springframework.stereotype.Repository;

/**
 * <p>创建时间: 2021/5/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Repository
public interface AccountDao extends BaseMapper<Account> {
}
