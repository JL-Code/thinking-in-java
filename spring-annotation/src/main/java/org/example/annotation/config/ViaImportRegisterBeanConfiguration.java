package org.example.annotation.config;

import org.example.annotation.bean.HelloBean;
import org.example.annotation.bean.ScopeBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/5 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
// @Import 注册 Bean，默认 Bean ID 为全类名。
// TODO: Import 如何制定 Bean 的 Scope 呢？
@Import({HelloBean.class, ScopeBean.class})
public class ViaImportRegisterBeanConfiguration {
}
