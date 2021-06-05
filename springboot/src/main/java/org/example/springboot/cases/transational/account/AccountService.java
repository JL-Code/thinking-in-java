package org.example.springboot.cases.transational.account;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * <p>创建时间: 2021/5/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface AccountService {

    /**
     * 转账
     *
     * @param fromAccount 转出金额的银行账户
     * @param toAccount   转入金额的银行账户
     * @param amount      金额
     * @return 转账成功标识，true 表示成功，反之则失败。
     */
    @Transactional(rollbackFor = Exception.class)
    boolean transfer(String fromAccount, String toAccount, BigDecimal amount);
}
