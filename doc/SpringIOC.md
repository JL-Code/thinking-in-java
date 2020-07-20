# Spring IOC

## 1. Bean LifeCycle （生命周期）

问题：

1. Bean 的创建流程是怎么样的？
2. Bean 的属性是何时赋值完成的？
3. Bean 中的循环依赖是如何处理的？
4. Bean 默认是单例的，如何变成多实例？
5. 如何存储单例 Bean，Bean是如何被索引查找到的？
6. Bean 的信息是如何被收集到 ApplicationContex 中的？
7. Bean 是如何存放的？
8. BeanDefinition 
9. Bean 的作用域？

## 2. Bean 的初始化

### 2.1 @Bean 提供 initMethod、destroyMethod 属性

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

### 2.2 Spring InitializingBean 、DisposableBean

> InitializingBean、DisposableBean 由 org.springframework.beans.factory 包提供，Spring 会负责调用。

```java
public interface InitializingBean {

	/**
	 * Invoked by a BeanFactory after it has set all bean properties supplied
	 * (and satisfied BeanFactoryAware and ApplicationContextAware).
	 * <p>This method allows the bean instance to perform initialization only
	 * possible when all bean properties have been set and to throw an
	 * exception in the event of misconfiguration.
	 * @throws Exception in the event of misconfiguration (such
	 * as failure to set an essential property) or if initialization fails.
	 */
	void afterPropertiesSet() throws Exception;
}

public interface DisposableBean {

	/**
	 * Invoked by a BeanFactory on destruction of a singleton.
	 * @throws Exception in case of shutdown errors.
	 * Exceptions will get logged but not rethrown to allow
	 * other beans to release their resources too.
	 */
	void destroy() throws Exception;
}
```



### 2.3 JSR250 @PostConstruct、@PreDestroy

```java
public class LifeCycleOfJSR250Bean {
    @PostConstruct
    public void postConstruct() {
        System.out.println("LifeCycleOfJSR250Bean PostConstruct ...");
    }
    @PreDestroy
    public void preDestroy() {
        System.out.println("LifeCycleOfJSR250Bean PreDestroy ...");
    }
}
```



### 2.4 Spring BeanPostProcessor

> BeanPostProcessor 接口提供了postProcessBeforeInitialization、postProcessAfterInitialization  函数。

postProcessBeforeInitialization



### 2.5 @Scope 指定 Bean 的作用域

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



## getBean 流程

### AbstractApplicationContext

refresh 方法：

```java
@Override
public void refresh() throws BeansException, IllegalStateException {
   synchronized (this.startupShutdownMonitor) {
      // Prepare this context for refreshing.
      // 准备此上下文以进行刷新。
      prepareRefresh();

      // Tell the subclass to refresh the internal bean factory.
      // 告诉子类刷新内部 bean 工厂。
      ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

      // Prepare the bean factory for use in this context.
      // 准备在此上下文中使用的 bean 工厂。
      prepareBeanFactory(beanFactory);

      try {
         // Allows post-processing of the bean factory in context subclasses.
         // 允许在上下文子类中对 bean 工厂进行后处理。
         postProcessBeanFactory(beanFactory);

         // Invoke factory processors registered as beans in the context.
         // 调用在上下文中注册为 bean 的工厂处理器。
         invokeBeanFactoryPostProcessors(beanFactory);

         // Register bean processors that intercept bean creation.
         // 注册拦截 Bean 创建的 Bean 处理器。
         registerBeanPostProcessors(beanFactory);

         // Initialize message source for this context.
         // 为此上下文初始化消息源。
         initMessageSource();

         // Initialize event multicaster for this context.
         // 为此上下文初始化事件多播器。(委托吗？)
         initApplicationEventMulticaster();

         // Initialize other special beans in specific context subclasses.
         // 在特定上下文子类中初始化其他特殊 bean。
         onRefresh();

         // Check for listener beans and register them.
         // 检查侦听器 bean 并注册它们。
         registerListeners();

         // Instantiate all remaining (non-lazy-init) singletons.
         // 实例化所有剩余的（非延迟初始化）单例。
         finishBeanFactoryInitialization(beanFactory);

         // Last step: publish corresponding event.
         // 最后一步：发布相应的事件。
         finishRefresh();
      }

      catch (BeansException ex) {
         if (logger.isWarnEnabled()) {
            logger.warn("Exception encountered during context initialization - " +
                  "cancelling refresh attempt: " + ex);
         }

         // Destroy already created singletons to avoid dangling resources.
         // 销毁已经创建的单实例以避免悬空资源。
         destroyBeans();

         // Reset 'active' flag.
         // 重置 “active” 标志。
         cancelRefresh(ex);

         // Propagate exception to caller.
         // 将异常传播到调用方
         throw ex;
      }

      finally {
         // Reset common introspection caches in Spring's core, since we
         // might not ever need metadata for singleton beans anymore...
         //重置 Spring 核心中的公共内省缓存，因为我们可能不再需要单例 bean 的元数据了。。。
         resetCommonCaches();
      }
   }
}
```

finishBeanFactoryInitialization 方法：

```java
/**
* Finish the initialization of this context's bean factory,
* initializing all remaining singleton beans.
*/
protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
    // Initialize conversion service for this context.
    // 初始化此上下文的转换服务。
    if (beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME) &&
        beanFactory.isTypeMatch(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class)) {
        beanFactory.setConversionService(
            beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class));
    }

    // Register a default embedded value resolver if no bean post-processor
    // (such as a PropertyPlaceholderConfigurer bean) registered any before:
    // at this point, primarily for resolution in annotation attribute values.
    
    if (!beanFactory.hasEmbeddedValueResolver()) {
        beanFactory.addEmbeddedValueResolver(strVal -> getEnvironment().resolvePlaceholders(strVal));
    }

    // Initialize LoadTimeWeaverAware beans early to allow for registering their transformers early.
    String[] weaverAwareNames = beanFactory.getBeanNamesForType(LoadTimeWeaverAware.class, false, false);
    for (String weaverAwareName : weaverAwareNames) {
        getBean(weaverAwareName);
    }

    // Stop using the temporary ClassLoader for type matching.
    beanFactory.setTempClassLoader(null);

    // Allow for caching all bean definition metadata, not expecting further changes.
    beanFactory.freezeConfiguration();

    // Instantiate all remaining (non-lazy-init) singletons.
    beanFactory.preInstantiateSingletons();
}
```





### AbstractBeanFactory 

`AbstractBeanFactory ` 内 `doGetBean ` 方法内容：

```java
protected <T> T doGetBean(
			final String name, final Class<T> requiredType, final Object[] args, boolean typeCheckOnly)
			throws BeansException {
		// 1.1. 去除 & （解除工厂引用前缀）
		// 1.2.确定 bean name 原始名称，会从别名集合中查找对应记录返回真正的名称
		final String beanName = transformedBeanName(name);
		Object bean;

		// Eagerly check singleton cache for manually registered singletons.
    	// 2. 认真检查单例缓存是否有手动注册的单例。
		Object sharedInstance = getSingleton(beanName);
    
		if (sharedInstance != null && args == null) {
			if (logger.isDebugEnabled()) {
				if (isSingletonCurrentlyInCreation(beanName)) {
					logger.debug("Returning eagerly cached instance of singleton bean '" + beanName +
							"' that is not fully initialized yet - a consequence of a circular reference");
				}
				else {
					logger.debug("Returning cached instance of singleton bean '" + beanName + "'");
				}
			}
            // 2.1 
			bean = getObjectForBeanInstance(sharedInstance, name, beanName, null);
		}

		else {
			// Fail if we're already creating this bean instance:
			// We're assumably within a circular reference.
			if (isPrototypeCurrentlyInCreation(beanName)) {
				throw new BeanCurrentlyInCreationException(beanName);
			}

			// Check if bean definition exists in this factory.
			BeanFactory parentBeanFactory = getParentBeanFactory();
			if (parentBeanFactory != null && !containsBeanDefinition(beanName)) {
				// Not found -> check parent.
				String nameToLookup = originalBeanName(name);
				if (args != null) {
					// Delegation to parent with explicit args.
					return (T) parentBeanFactory.getBean(nameToLookup, args);
				}
				else {
					// No args -> delegate to standard getBean method.
					return parentBeanFactory.getBean(nameToLookup, requiredType);
				}
			}

			if (!typeCheckOnly) {
				markBeanAsCreated(beanName);
			}

			try {
				final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
				checkMergedBeanDefinition(mbd, beanName, args);

				// Guarantee initialization of beans that the current bean depends on.
				String[] dependsOn = mbd.getDependsOn();
				if (dependsOn != null) {
					for (String dep : dependsOn) {
						if (isDependent(beanName, dep)) {
							throw new BeanCreationException(mbd.getResourceDescription(), beanName,
									"Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
						}
						registerDependentBean(dep, beanName);
						getBean(dep);
					}
				}

				// Create bean instance.
				if (mbd.isSingleton()) {
					sharedInstance = getSingleton(beanName, new ObjectFactory<Object>() {
						@Override
						public Object getObject() throws BeansException {
							try {
								return createBean(beanName, mbd, args);
							}
							catch (BeansException ex) {
								// Explicitly remove instance from singleton cache: It might have been put there
								// eagerly by the creation process, to allow for circular reference resolution.
								// Also remove any beans that received a temporary reference to the bean.
								destroySingleton(beanName);
								throw ex;
							}
						}
					});
					bean = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
				}

				else if (mbd.isPrototype()) {
					// It's a prototype -> create a new instance.
					Object prototypeInstance = null;
					try {
						beforePrototypeCreation(beanName);
						prototypeInstance = createBean(beanName, mbd, args);
					}
					finally {
						afterPrototypeCreation(beanName);
					}
					bean = getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
				}

				else {
					String scopeName = mbd.getScope();
					final Scope scope = this.scopes.get(scopeName);
					if (scope == null) {
						throw new IllegalStateException("No Scope registered for scope name '" + scopeName + "'");
					}
					try {
						Object scopedInstance = scope.get(beanName, new ObjectFactory<Object>() {
							@Override
							public Object getObject() throws BeansException {
								beforePrototypeCreation(beanName);
								try {
									return createBean(beanName, mbd, args);
								}
								finally {
									afterPrototypeCreation(beanName);
								}
							}
						});
						bean = getObjectForBeanInstance(scopedInstance, name, beanName, mbd);
					}
					catch (IllegalStateException ex) {
						throw new BeanCreationException(beanName,
								"Scope '" + scopeName + "' is not active for the current thread; consider " +
								"defining a scoped proxy for this bean if you intend to refer to it from a singleton",
								ex);
					}
				}
			}
			catch (BeansException ex) {
				cleanupAfterBeanCreationFailure(beanName);
				throw ex;
			}
		}

		// Check if required type matches the type of the actual bean instance.
		if (requiredType != null && bean != null && !requiredType.isInstance(bean)) {
			try {
				return getTypeConverter().convertIfNecessary(bean, requiredType);
			}
			catch (TypeMismatchException ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("Failed to convert bean '" + name + "' to required type '" +
							ClassUtils.getQualifiedName(requiredType) + "'", ex);
				}
				throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
			}
		}
		return (T) bean;
	}
```

### DefaultSingletonBeanRegistry  

DefaultSingletonBeanRegistry  类 getSingleton 方法：

> Return the  singleton object registered under the given name. Checks already instantiated singletons and also allows for an early reference to a currently created singleton (resolving a circular reference).
> 返回以给定名称注册的（原始）单例对象。检查已经实例化的singleton，并允许早期引用当前创建的singleton（解析循环引用）。

```java
protected Object getSingleton(String beanName, boolean allowEarlyReference) {
    
    Object singletonObject = this.singletonObjects.get(beanName);
    // isSingletonCurrentlyInCreation(beanName) 返回指定的singleton bean当前是否正在创建中
    if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
        synchronized (this.singletonObjects) {
            singletonObject = this.earlySingletonObjects.get(beanName);
            if (singletonObject == null && allowEarlyReference) {
                ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    this.earlySingletonObjects.put(beanName, singletonObject);
                    this.singletonFactories.remove(beanName);
                }
            }
        }
    }
    return (singletonObject != NULL_OBJECT ? singletonObject : null);
}
```

### DefaultListableBeanFactory

preInstantiateSingletons 方法：

```java
	/**
	 * Ensure that all non-lazy-init singletons are instantiated, also considering
	 * {@link org.springframework.beans.factory.FactoryBean FactoryBeans}.
	 * Typically invoked at the end of factory setup, if desired.
	 * @throws BeansException if one of the singleton beans could not be created.
	 * Note: This may have left the factory with some beans already initialized!
	 * Call {@link #destroySingletons()} for full cleanup in this case.
	 * @see #destroySingletons()
	 */
@Override
public void preInstantiateSingletons() throws BeansException {
    if (logger.isTraceEnabled()) {
        logger.trace("Pre-instantiating singletons in " + this);
    }

    // Iterate over a copy to allow for init methods which in turn register new bean definitions.
    // While this may not be part of the regular factory bootstrap, it does otherwise work fine.
    List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);

    // Trigger initialization of all non-lazy singleton beans...
    for (String beanName : beanNames) {
        RootBeanDefinition bd = getMergedLocalBeanDefinition(beanName);
        if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) {
            if (isFactoryBean(beanName)) {
                Object bean = getBean(FACTORY_BEAN_PREFIX + beanName);
                if (bean instanceof FactoryBean) {
                    FactoryBean<?> factory = (FactoryBean<?>) bean;
                    boolean isEagerInit;
                    if (System.getSecurityManager() != null && factory instanceof SmartFactoryBean) {
                        isEagerInit = AccessController.doPrivileged(
                            (PrivilegedAction<Boolean>) ((SmartFactoryBean<?>) factory)::isEagerInit,
                            getAccessControlContext());
                    }
                    else {
                        isEagerInit = (factory instanceof SmartFactoryBean &&
                                       ((SmartFactoryBean<?>) factory).isEagerInit());
                    }
                    if (isEagerInit) {
                        getBean(beanName);
                    }
                }
            }
            else {
                getBean(beanName);
            }
        }
    }

    // Trigger post-initialization callback for all applicable beans...
    for (String beanName : beanNames) {
        Object singletonInstance = getSingleton(beanName);
        if (singletonInstance instanceof SmartInitializingSingleton) {
            SmartInitializingSingleton smartSingleton = (SmartInitializingSingleton) singletonInstance;
            if (System.getSecurityManager() != null) {
                AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
                    smartSingleton.afterSingletonsInstantiated();
                    return null;
                }, getAccessControlContext());
            }
            else {
                smartSingleton.afterSingletonsInstantiated();
            }
        }
    }
}
```



## 总结