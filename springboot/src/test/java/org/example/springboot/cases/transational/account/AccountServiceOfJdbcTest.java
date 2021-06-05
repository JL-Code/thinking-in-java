package org.example.springboot.cases.transational.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/5/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@SpringBootTest
class AccountServiceOfJdbcTest {

    @Autowired
    AccountServiceOfJdbc accountServiceOfJdbc;

    @Test
    void transfer() throws SQLException {
        String fromAccount = "zhangsan";
        String toAccount = "lisi";
        BigDecimal amount = BigDecimal.valueOf(1000);

        accountServiceOfJdbc.transfer(fromAccount, toAccount, amount);
    }
}