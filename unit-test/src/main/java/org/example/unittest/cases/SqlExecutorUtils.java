package org.example.unittest.cases;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>创建时间: 2021/4/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class SqlExecutorUtils {



    /**
     * 执行动态SQL
     *
     * @param sql sql脚本
     * @return 结果集
     */
    public List<LinkedHashMap<String, Object>> executeQuerySql(String sql) {
        return sqlExecutorDao.executeQuerySql(sql);
    }


}
