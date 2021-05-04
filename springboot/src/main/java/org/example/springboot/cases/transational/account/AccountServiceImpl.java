package org.example.springboot.cases.transational.account;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.springboot.cases.transational.dao.AccountDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * <p>创建时间: 2021/5/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {

    private final AccountDao dao;

    public AccountServiceImpl(AccountDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean transfer(String fromAccount, String toAccount, BigDecimal amount) {

        // 1. 系统: 检查张三账户余额是否大于等于 1000。
        // 2. 系统: 扣除张三账户余额 1000。
        // 3. 系统: 增加李四账户余额 1000。
        Account fAccount = dao.selectOne(Wrappers.<Account>lambdaQuery().eq(Account::getAccountName, fromAccount));
        Account tAccount = dao.selectOne(Wrappers.<Account>lambdaQuery().eq(Account::getAccountName, toAccount));

        Assert.state(fAccount.getBalance().compareTo(amount) != -1, "余额不足！");

        fAccount.setBalance(fAccount.getBalance().subtract(amount));
        tAccount.setBalance(tAccount.getBalance().add(amount));

        // 同生共死
        boolean result = saveOrUpdateBatch(Arrays.asList(fAccount, tAccount));

        return result;
    }

}
