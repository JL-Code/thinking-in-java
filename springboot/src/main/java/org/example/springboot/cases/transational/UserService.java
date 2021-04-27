package org.example.springboot.cases.transational;

public interface UserService {
    /**
     * 声明式事务案例
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 命令式事务案例
     *
     * @param user
     * @return
     */
    User commandTransactionCase(User user);
}
