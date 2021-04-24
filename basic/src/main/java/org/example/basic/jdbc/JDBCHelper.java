package org.example.basic.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>创建时间: 2021/3/7 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class JDBCHelper {

    public static List<Map<String, Object>> testQueryNoParameterSafety(String sql) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String url = "jdbc:h2:~/h2/test";
        // try(resource) 会自动释放资源
        // Connection、Statement、ResultSet 在不使用后都需要关闭
        try (Connection conn = DriverManager.getConnection(url, "sa", "")) {
            // Statement 有 SQL 参数注入风险，可以使用 PreparedStatement 替换
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        Map<String, Object> tuple = new HashMap<>(16);
                        // 注意：索引从1开始
                        String id = rs.getString(1);
                        String name = rs.getString(2);
                        tuple.put("id", id);
                        tuple.put("name", name);
                        result.add(tuple);
                    }
                }
            }
        }

        return result;
    }

    public static List<Map<String, Object>> testQueryParameterSafety(String username, String password) throws SQLException {
        String sql = "select * from cloud_user where username =? and password=?";
        List<Map<String, Object>> result = new ArrayList<>();
        String url = "jdbc:h2:~/h2/test";
        // try(resource) 会自动释放资源
        // Connection、Statement、ResultSet 在不使用后都需要关闭
        try (Connection conn = DriverManager.getConnection(url, "sa", "")) {
            // Statement 有 SQL 参数注入风险，可以使用 PreparedStatement 替换
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // 安全填充参数
                pstmt.setString(1, username);
                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Map<String, Object> tuple = new HashMap<>(16);
                        // 注意：索引从1开始
                        String id = rs.getString(1);
                        String name = rs.getString(2);
                        tuple.put("id", id);
                        tuple.put("name", name);
                        result.add(tuple);
                    }
                }
            }
        }

        return result;
    }

    public static List<Map<String, Object>> getResultSetMetaData(String sqlStatement) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/highzap_cloud?serverTimezone=GMT%2B8&useSSL=false&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
        // try(resource) 会自动释放资源
        // Connection、Statement、ResultSet 在不使用后都需要关闭
        try (Connection conn = DriverManager.getConnection(url, "root", "12345678")) {
            // Statement 有 SQL 参数注入风险，可以使用 PreparedStatement 替换
            try (PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
                // 安全填充参数
//                pstmt.setString(1, username);
//                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {

                    /**
                     * fields
                     * field data strcut
                     * com.mysql.cj.result.Field@cc43f62[dbName=highzap_cloud,tableName=cloud_user,originalTableName=cloud_user,columnName=id,originalColumnName=id,mysqlType=254(FIELD_TYPE_CHAR),sqlType=1,flags= PRIMARY_KEY, charsetIndex=45, charsetName=utf-8]
                     */

                    ResultSetMetaData metaData = rs.getMetaData();

                    System.out.println(metaData);

                    while (rs.next()) {
                        Map<String, Object> tuple = new HashMap<>(16);
                        // 注意：索引从1开始
                        String id = rs.getString(1);
                        String name = rs.getString(2);
                        tuple.put("id", id);
                        tuple.put("name", name);
                        result.add(tuple);
                    }
                }
            }
        }

        return result;
    }
}
