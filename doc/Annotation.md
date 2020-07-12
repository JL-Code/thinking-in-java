# 1. Spring Annotation

## 1.1. 产生背景

## 1.2. Bean

> `Bean` 的定义：`Bean` 是一个符合规范的 Java 公共类，规范包括以下几点：
>
>  1. Bean 应该默认拥有无参构造函数。
>  2. Bean 应该实现 `java.io.serializable`
>  3. Bean 应该提供 `getter`、`setter` 方法用于操作内部状态。
> `@Bean` 用于将 `Bean` 注册到 `ApplicationContext`，组件 `ID` 默认为小驼峰类名或方法名。

## 1.3. Bean 注册

**Bean 的注册方式：**

1. `@Component` + `@ComponentScan` 多用于自有项目中 `Bean` 注册。该方案需要显式的为 `Bean` 标注 `@Component` 注解才能被 `@ComponentScan` 扫描到，缺点是在三方库中无法显式标注注解导致无法注册 `Bean`，优点在于在自有项目中配置简单便捷。
2. `@Bean` 多用于第三方库中的 `Bean` 注册（解决第一种方式带来的问题）。
3. `@Import`
4. 使用 Spring 提供的 FactoryBean 🏭 注册 Bean 组件。

### 1.3.1. @Component + @ComponentScan 组合扫描注册 Bean

`@ComponentScan` 默认会去扫描带有 `@Component`、`@Repository`、
`@Service`、`@Controller` 注解的类。扫描范围为: `@ComponentScan` 所注释的类的当前包及其子包。

@ComponentScan 源码：👇

```java
省略 ...
@Repeatable(ComponentScans.class)
public @interface ComponentScan{
    /**
    * 默认启用对包含 @Component @Repository @Service @Controller 注解的类进行扫描。
    */
    boolean useDefaultFilters() default true;
}
```

#### 1.3.1.1. exclude package

> 排除 `@ComponentScan` 中的一些类，使其不被加入到 `IOC` 容器中。当前支持的 FilterType：ANNOTATION、ASSIGNABLE_TYPE、ASPECTJ、REGEX、CUSTOM。

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
                        // 按照给定类型过滤
                        type = FilterType.ASSIGNABLE_TYPE,
                        value = HelloService.class
                )
        }
)
public class CustomFilterComponentScanConfiguration {
}
```

**自定义 FilterType :**
> 自定义 `FilterType` 必须是 `org.springframework.core.type.filter.TypeFilter` 的实现类。

```java
public class CustomFilterType implements TypeFilter {

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();

        // 扫描类名称包含er的类。
        if (className.contains("er")) {
            return true;
        }
        return false;
    }
}
```

#### 1.3.1.2. include package

同 **exclude package** 刚好相反。

### 1.3.2. 通过 @Bean 注解注册 Bean

```java
class Configration{
     /**
     * @Bean 注解默认规定方法名为 Bean ID，方法返回类型为 Bean 类型。
     * 默认为单例模式，随着 ApplicationContext 创建而创建，
     但可以通过 @Lazy 注解改变这种行为，
     * 将 Bean 的实例化延迟到第一次调用时。
     */
    @Bean
    public HelloBean getHelloBean() {
        return new HelloBean();
    }
}
```

### 1.3.3. @Lazy

> 指示是否要延迟初始化 bean。可以用于直接或间接用 `@Component` 注释的任何类，或用 `@Bean` 注释的方法。

[未完待续...]

### 1.3.4. @Import

> 用于注册不能通过 `@Component` 等注解注册的 `Bean` （例如：第三方依赖库）。

@Import 注册的 Bean， ID 是 Bean 的类全路径（例如：org.example.annotation.bean.LifeCycleTestOfBean）。

@Import 注册的 Bean，无法进一步自定义配置，比如手动指定初始化、销毁等自定义 Bean 生命周期方法。

@Import 源码：👇

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
...
public @interface Import {
    Class<?>[] value();
}
```



### 1.3.5. Spring FactoryBean 方式注册 Bean

> Spring 中有两种 `Bean`，一种是普通 Bean，另一种则是 `FactoryBean` 。FactoryBean 跟普通 Bean不同，其返回的对象不是指定类的一个实例，而是该 FactoryBean 的 getObject 方法所返回的对象。



FactoryBean 接口定义：👇

```java
public interface FactoryBean<T>{
  T getObject() throws Exception;
  Class<?> getObjectType();
  boolean isSingleton();
}
```

要想使用 FactoryBean 则必须实现这个 FactoryBean 接口。

#### 1.3.5.1 应用场景

MyBatis 的 SqlSessionFactoryBean 提供 SqlSessionFactory。

#### 1.3.5.2 应用案例

复杂 Bean 的创建时，可使用 FactoryBean 。【Get 不到点。。。😓】

#### 1.3.5.3 参考文档

1. [Spring FactoryBean应用](https://www.jianshu.com/p/6f0a59623090)

## 1.4. Bean LifeCycle （生命周期）

### @Bean 提供 initMethod、destroyMethod

`@Bean` 注解提供 `initMethod`、`destroyMethod` 属性来显式指定 `Bean` **初始化方法**和**销毁方法**。

代码示例：👇

```java
@Configuration
//@Import({LifeCycleTestOfBean.class}) @Import 导入的Bean，无法进一步自定义配置，比如手动指定初始化、销毁等自定义 Bean 生命周期方法
public class LifeCycleTestBeanConfiguration {
    /**
     * 容器会自动发现并注册 Bean 中的 无参公共 'close' or 'shutdown' 方法作为销毁方法. 若要禁止容器的这种行为
     * 可以通过设置显式指定 destroyMethod=“” 。
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public LifeCycleTestOfBean lifeCycleTestOfBean() {
        return new LifeCycleTestOfBean();
    }
}

public class LifeCycleTestOfBean {
    /**
     * 对象创建：
     * 单实例：容器启动后创建 Bean 对象
     * 多实例：每次获取 Bean 时创建对象。
     */
    public LifeCycleTestOfBean() {
        System.out.println("LifeCycleTestOfBean ...");
    }
    /**
     * 调用时机：对象创建完成，并且属性赋值完成后，调用 init-method 初始化方法。
     */
    public void init() {
        System.out.println("bean init ...");
    }
    /**
     * 容器会自动发现并注册 Bean 中的 无参公共 'close' or 'shutdown' 方法作为销毁方法. 若要禁止容器的这种行为
     * 可以通过设置显式指定 destroyMethod=“” 。
     * 调用时机：
     * 单实例：容器关闭的时候调用。
     * 多实例：容器不管理注销方法
     */
    public void close() {
        System.out.println("bean destroy ...");
    }
}
```



### 1.4.1. @Scope

指定 `Bean` 的生命周期可作用于拥有 `@Component`、`@Bean` 注释的类或方法上，`Scope` 共拥有四种选项分别为：

1. `ConfigurableBeanFactory#SCOPE_PROTOTYPE` **prototype** （原型）
2. `ConfigurableBeanFactory#SCOPE_SINGLETON` **singleton** （单例）
3. （**仅存在于 Web 应用**）`org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST`
4. （**仅存在于 Web 应用**）`org.springframework.web.context.WebApplicationContext#SCOPE_SESSION`

可以通过实现 `org.springframework.beans.factory.config.Scope` + `ConfigurableBeanFactory registerScope()` 完成自定义 `Scope` 的注册。

```java
// =========== Bean ==================
@Data
class HelloBean{
    private String name;
    private int age;
}
// =========== Bean 配置类 ============
@Configuration
public class DifferentScopeLifecycleOfBeanConfiguration {

    @Bean
    public HelloBean defaultSingleton() {
        return new HelloBean();
    }

    @Bean
    @Scope("prototype")
    public HelloBean specifiedPrototype() {
        return new HelloBean();
    }
}
// =========测试代码=============
class DifferentScopeLifecycleOfBeanConfigurationTest {
    ApplicationContext context =
            new AnnotationConfigApplicationContext(DifferentScopeLifecycleOfBeanConfiguration.class);
    @BeforeEach
    public void beforeEach() {
        System.out.println("当前 ApplicationContext 中存在的 Bean：");
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
    }
    @Test
    @DisplayName("测试 @Scope 默认作用域 singleton,期望每次获取到的 Bean 都是同一个实例")
    public void testSingletonForDefaultScope() {
        HelloBean singleton1 = (HelloBean) context.getBean("defaultSingleton");
        HelloBean singleton2 = (HelloBean) context.getBean("defaultSingleton");
        Assertions.assertNotNull(singleton1);
        Assertions.assertEquals(singleton1, singleton2);
    }
    @Test
    @DisplayName("测试 @Scope 作用域 prototype, 期望每次获取到的 Bean 都不是同一个实例")
    public void testPrototypeForSpecifiedScope() {
        HelloBean singleton1 = (HelloBean) context.getBean("specifiedPrototype");
        HelloBean singleton2 = (HelloBean) context.getBean("specifiedPrototype");
        Assertions.assertNotNull(singleton1);
        Assertions.assertNotEquals(singleton1, singleton2);
    }
}
```



## @Conditional

## @Configuration

## 1.5. 属性赋值

### 1.5.1. @PropertySource

> 加载外部配置文件数据。

## 1.6. AOP
