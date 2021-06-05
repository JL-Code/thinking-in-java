package org.example.springboot.cases.transational;

public interface UserService {
    /**
     * 声明式事务案例
     *
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 嵌套调用事务方法
     */
    boolean nestedCallTransactionMethod(User user);

    /**
     * 命令式事务案例
     *
     * @param user
     * @return
     */
    User imperativeTransactionCase(User user);

    /**
     * 移除用户
     *
     * @param userId 用户ID
     * @return
     */
    boolean remove(String userId);
}
