package org.example.basic.sqlparser;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/5/19 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class SqlParserAstCaseTest {

    /**
     * TODO: druid 不支持 as 语法字段的解析
     */
    @Test
    public void sqlParserAstCase() {
        final DbType dbType = JdbcConstants.MYSQL; // 可以是ORACLE、POSTGRESQL、SQLSERVER、ODPS等

        String sql = "SELECT v.id,\n" +
                "               v.view_name          as name,\n" +
                "               v.function_module_id as parent_id,\n" +
                "               v.sort,\n" +
                "               v.disabled,\n" +
                "               ''     as application_id,\n" +
                "               ''                   as remark,\n" +
                "               3                    as hierarchy,\n" +
                "               3                    as max_hierarchy,\n" +
                "               '视图'                 as category,\n" +
                "               v.defaulted\n" +
                "        FROM mp_view v\n" +
                "        where v.deleted = 0\n" +
                "        UNION ALL\n" +
                "        SELECT m.id,\n" +
                "               m.function_name AS name,\n" +
                "               m.parent_id,\n" +
                "               m.order_code    as sort,\n" +
                "               m.disabled,\n" +
                "               m.application_id,\n" +
                "               m.remark,\n" +
                "               m.hierarchy,\n" +
                "               3               as max_hierarchy,\n" +
                "               '模块'            as category,\n" +
                "               false           as defaulted\n" +
                "        FROM mp_function_module m\n" +
                "        where m.application_id = '';\n" +
                "\n";

        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
        // 创建一个 AST 访问器
        SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(dbType);

        ExportTableAliasVisitor visitor = new ExportTableAliasVisitor();


        for (SQLStatement stmt : stmtList) {
            stmt.accept(visitor);
            SQLTableSource tableSource = visitor.getAliasMap().get("v");
            System.out.println(tableSource);
        }
//        for (SQLStatement stmt : stmtList) {
//            // 为 statement 应用访问器
//            stmt.accept(statVisitor);
//
//            System.out.println(statVisitor.getColumns()); // [mp_view.id, mp_view.view_name, mp_view.function_module_id, mp_view.sort, mp_view.disabled, mp_view.defaulted, mp_view.deleted, mp_function_module.id, mp_function_module.function_name, mp_function_module.parent_id, mp_function_module.order_code, mp_function_module.disabled, mp_function_module.application_id, mp_function_module.remark, mp_function_module.hierarchy]
//            System.out.println(statVisitor.getTables()); // {mp_view=Select, mp_function_module=Select}
//            System.out.println(statVisitor.getConditions()); // [mp_view.deleted = 0, mp_function_module.application_id = ]
//        }

    }
}