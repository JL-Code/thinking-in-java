# 注解 Annotation

## 产生背景

## 常用注解

### @ComponentScan

`@ComponentScan` 默认会去扫描带有 `@Component`、`@Repository`、
`@Service`、`@Controller` 注解的类。

@ComponentScan 源码：👇

```java
省略 ...
@Repeatable(ComponentScans.class)
public @interface ComponentScan{
    /**
    * Indicates whether automatic detection of classes annotated with {@code @Component}
    * {@code @Repository}, {@code @Service}, or {@code @Controller} should be enabled.
    */
    boolean useDefaultFilters() default true;
}
```

#### exclude package

> 排除 `@ComponentScan` 中的一些类，使其不被加入到 `IOC` 容器中。

excludeFilter 示例代码：👇

```java
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
```

#### include package

### @Component

### @Configuration

### @Import

### @SpringBootApplication

### @Override

### SuppressWarnings

> 压制警告信息⚠，忽略警告。
