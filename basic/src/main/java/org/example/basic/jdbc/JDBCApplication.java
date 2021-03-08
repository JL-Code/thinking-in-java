package org.example.basic.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间: 2021/3/7 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class JDBCApplication {
    public static void main(String[] args) {
        String sql = "select id,name from cloud_user";
        try {
            List<Map<String, Object>> records = JDBCHelper.testQueryNoParameterSafety(sql);
            List<Map<String, Object>> records2 = JDBCHelper.testQueryParameterSafety("18580687918", "123");
            System.out.println(records);
            System.out.println(records2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
