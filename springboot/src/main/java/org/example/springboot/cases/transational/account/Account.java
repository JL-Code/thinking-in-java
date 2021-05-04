package org.example.springboot.cases.transational.account;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>创建时间: 2021/5/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
@TableName("account")
public class Account {
    private Long id;
    private String accountName;
    private BigDecimal balance;
}
