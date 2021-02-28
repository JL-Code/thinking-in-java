package org.example.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>创建时间: 2021/2/26 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@SpringBootApplication
public class MyBatisExampleApplication {
    public static void main(String[] args) {
        // 使 JDK 动态代理生成的字节码文件保存到磁盘
        // 参考：https://www.pianshen.com/article/84631659401/
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        SpringApplication.run(MyBatisExampleApplication.class, args);
    }
}
