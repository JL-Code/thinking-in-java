package org.example.basic.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/4/21 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class JDBCHelperTest {

    @Test
    void getResultSetMetaData() throws SQLException {
        String sql = "select * from cloud_user";
        JDBCHelper.getResultSetMetaData(sql);
    }
}