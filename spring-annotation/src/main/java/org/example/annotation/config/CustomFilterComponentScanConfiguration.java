package org.example.annotation.config;

import org.example.annotation.service.HelloService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
@ComponentScan(
        // 设置扫描基准范围
        value = "org.example.annotation",
        // 关闭默认的过滤器
        // useDefaultFilters = true,
        // 排除包含 @Controller 注解的类。
        // FilterType：ANNOTATION、ASSIGNABLE_TYPE、ASPECTJ、REGEX、CUSTOM
        excludeFilters = {
                @ComponentScan.Filter(
                        // 注解过滤
                        type = FilterType.ANNOTATION,
                        value = Controller.class
                ),
                @ComponentScan.Filter(
                        // 自定类过滤
                        type = FilterType.ASSIGNABLE_TYPE,
                        value = HelloService.class
                )
        }
)
public class CustomFilterComponentScanConfiguration {
}
