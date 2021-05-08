package org.example.unittest.cases.spy;

import java.util.Date;

/**
 * <p>创建时间: 2021/5/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class UserService {
    public String getUsername() {
        return "username" + new Date();
    }

    public boolean remove() {
        String username = getUsername();
        if (username == "mocked") {
            return true;
        } else {
            System.out.println("username is not mocked");
            return false;
        }
    }
}
