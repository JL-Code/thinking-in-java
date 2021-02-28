package org.example.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>创建时间: 2021/2/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@MapperScan("org.example.mybatis.repository")
@Configuration
public class MybatisPlusConfig {

    /*
     * 分页插件，自动识别数据库类型 多租户，请参考官网【插件扩展】
     */
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        return new MybatisPlusInterceptor();
//    }
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
