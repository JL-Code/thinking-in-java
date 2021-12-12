package org.example.springboot.cases.transational;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.example.springboot.cases.transational.dao")
public class MyBatisPlusConfig {

//    @Bean
//    public DataSource getMySqlDataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//
//        // 配置数据库连接信息
//        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/springboot-db?serverTimezone=GMT%2B8&useSSL=false&useUnicode=true&characterEncoding=utf-8" +
//                "&allowPublicKeyRetrieval=true");
//        dataSource.setUsername("root");
//        dataSource.setPassword("12345678");
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//
//        // 配置连接池信息
//        dataSource.setMaximumPoolSize(20);
//        dataSource.setMinimumIdle(10);
//
//        return dataSource;
//    }
//
//    @Bean
//    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) {
//
//        TransactionFactory transactionFactory =  new JdbcTransactionFactory();
//
//        Environment environment = new Environment("development", transactionFactory, dataSource);
//
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
//
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
//
//        return sqlSessionFactory;
//    }
}
