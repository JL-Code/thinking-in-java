# SpringBean 自动装配

> SpringContext 通过依赖注入（DI）的方式，完成对 IOC 容器中的各个组件的依赖关系赋值，可以通过 `@Autowired`、`@Resource`(JSR-250)、`@Inject`（JSR-330） 实现依赖的自动注入。



| Annotation | Package                          | Source       | Description                                                  |
| :--------- | :------------------------------- | :----------- | :----------------------------------------------------------- |
| @Autowired | javax.annotation                 | Java JSR-250 | 优先 **byType** 装配，若多个则使用 **byName**。              |
| @Resource  | javax.inject                     | Java JSR-330 | 优先 **byName** 装配，若多个则报错；可以显式指定 name、type 进行装配。 |
| @Inject    | org.springframework.bean.factory | Spring 2.5+  | 项目需显式依赖 `javax.inject:javax.inject:1`。               |
| @Qualifier | org.springframework.bean.factory | Spring 2.5+  | 显式通过 `name` 显示装配依赖（`@Autowired` ✅、`@Inject`✅、`@Resource`❌） |
| @Primary   | org.springframework.bean.factory | Spring 2.5+  | 存在多个相同类型的 `Bean` 时可以通过 `@Primary` 指定优先装配顺序（`@Autowired` ✅、`@Inject` ✅、`@Resource`❌）。 |
| @Lazy      |                                  |              | 与 @Bean 结合使用，表示 Bean 延迟加载（用到时才加载）。      |





## `@Autowired`

> 由 Spring 提供的自动装配注解。

```java
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    // 声明是否需要强制注入的依赖项 默认为：true。
    boolean required() default true;
}
```

装配顺序：

1. `@Autowired` **默认是按照类型装配**：`ApplicationContext.getBean(HelloDao.class)`。
2. 如果找到多个相同类型的 `Bean` 则将属性的名称作为 `Bean ID` 去容器中寻找：`ApplicationContext.getBean("helloDao")`。
3. 可以通过 `@Qualifier` 显式让容器装配指定 `ID` 的 `Bean`。
4. `@Autowired` 提供 `required` 属性，声明是否需要强制注入的依赖项，设置为 `false` 时可以忽略依赖（当依赖不存在于容器时）。

<font color="red">注：由于`@Autowired`在存在多个相同类型 `Bean` 时，`@Autowired` 采用的是按属性名称查找 `Bean`，所以属性名称不可随意书写 ✍️</font>。

## @Resource

> JSR-250 提供的注解。

```java
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface Resource {
    String name() default "";
    String lookup() default "";
    Class<?> type() default java.lang.Object.class;
    enum AuthenticationType {
            CONTAINER,
            APPLICATION
    }
    AuthenticationType authenticationType() default AuthenticationType.CONTAINER;
    boolean shareable() default true;
    String mappedName() default "";
    String description() default "";
}
```

装配顺序：

1. 如果同时指定了 `name` 和 `type` ，则从容器中找到唯一匹配的（`name + type`） `bean` 进行装配，找不到则抛出异常;
2. 如果指定了 `name` ，则从上下文中查找名称（`bean id`）匹配的 `bean` 进行装配，找不到则抛出异常;
3. 如果指定了 `type` ，则从上下文中找到类型匹配的唯一 `bean` 进行装配，**找不到或者找到多个，都会抛出异常**;
4. 如果既没有指定 `name` ，又没有指定 `type` ，则自动按照 `byName` 方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；

## @Inject

> `JSR-330` 提供的注解，可用于取代(`@Inject` 仅不支持 `required`) `@Autowired` , `@Inject` `java` 原生支持，不依赖第三方框架。

@Inject：

```java
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inject {
}
```

使用前需要添加依赖：Maven 👇

```xml
<dependency>
    <groupId>javax.inject</groupId>
    <artifactId>javax.inject</artifactId>
    <version>1</version>
</dependency>
```

## @Lazy

延迟加载

## xxxAware 类

## 工作机制

`AutowiredAnnotationBeanPostProcessor` : 解析完成自动装配工作。

## 参考文档

1. [尚硅谷Spring注解驱动教程(雷丰阳源码级讲解)](https://www.bilibili.com/video/BV1gW411W7wy?p=22)
