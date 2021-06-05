package org.example.unittest.cases.spy;

import java.util.Date;

/**
 * <p>创建时间: 2021/5/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public String getAccount() {
        String s = "account:" + new Date();
        return s;
    }
}
