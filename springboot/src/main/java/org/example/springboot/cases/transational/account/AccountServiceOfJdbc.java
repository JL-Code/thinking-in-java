package org.example.springboot.cases.transational.account;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.springboot.cases.transational.dao.AccountDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * <p>创建时间: 2021/5/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Component
public class AccountServiceOfJdbc {


    private final DataSource dataSource;
    private final TransactionTemplate template;
    private final AccountDao dao;

    public AccountServiceOfJdbc(DataSource dataSource, TransactionTemplate template, AccountDao dao) {
        // 1. 获取数据源
        this.dataSource = dataSource;
        this.template = template;
        this.dao = dao;
    }

    public void transfer(String fromAccount, String toAccount, BigDecimal amount) throws SQLException {
        // 1.获取数据库连接
        Connection connection = dataSource.getConnection();
        try {
            // 2.关闭自动提交事务
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            // 3.执行 SQL
            // 3.1.系统: 检查张三账户余额是否大于等于 1000。
            // 3.2.系统: 扣除张三账户余额 1000。
            // 3.3.系统: 增加李四账户余额 1000。
            statement.execute(String.format("update account set balance=balance-%s where account_name = '%s'", amount, fromAccount));
            statement.execute(String.format("update account set balance=balance+%s where account_name = '%s'", amount, toAccount));
            // 4.提交事务
            connection.commit();

        } catch (SQLException ex) {
            //5.程序发生意外时回滚事务
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    /**
     * 指令式事务管理
     *
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @return
     */
    public boolean transferOfImperative(String fromAccount, String toAccount, BigDecimal amount) {

        Boolean reuslt = template.<Boolean>execute(status -> {

            Account fAccount = dao.selectOne(Wrappers.<Account>lambdaQuery().eq(Account::getAccountName, fromAccount));
            Account tAccount = dao.selectOne(Wrappers.<Account>lambdaQuery().eq(Account::getAccountName, toAccount));

            Assert.state(fAccount.getBalance().compareTo(amount) != -1, "余额不足！");

            fAccount.setBalance(fAccount.getBalance().subtract(amount));
            tAccount.setBalance(tAccount.getBalance().add(amount));

            // 同生共死
            int fc = dao.updateById(fAccount);
            int tc = dao.updateById(tAccount);

            return (fc + tc == 2);

        });

        return reuslt == null ? false : reuslt;
    }
}
