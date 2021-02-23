package org.example.annotation.config;

import org.example.annotation.bean.TestSingleClassMultipleInterface;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>创建时间: 2021/1/31 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
@Import(TestSingleClassMultipleInterface.class)
public class TestSingleClassMultipleInterfaceConfig {
}
