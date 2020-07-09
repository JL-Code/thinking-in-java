package org.example.annotation.config;

import org.example.annotation.bean.HelloBean;
import org.example.annotation.config.testscope.TestScopeChildrenPackage;
import org.example.annotation.dao.HelloDao;
import org.example.annotation.service.HelloService;
import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * <p>描述:  </p>
 * <p>创建时间: 2020/7/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class AnnotationApplicationContextTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfigurationn.class);


    @BeforeEach
    public void beforeEach() {
        System.out.println("当前 ApplicationContext 中存在的 Bean：");
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
    }


    /**
     * 测试来自 @Bean 注解的 Bean。
     */
    @Test
    @DisplayName("测试获取由 @Bean 注解的类的实例")
    public void testBeanForAnnotation() {

        // 通过 Bean 类型获取 Bean 实例。
        HelloBean bean = context.getBean(HelloBean.class);
        Assertions.assertNotNull(bean);
    }

    @Test
    @DisplayName("@ComponentSacn 会扫描自身所在包的子包的类")
    public void testComponentSacnChildrenPackage() {

        // 通过 Bean 类型获取 Bean 实例。
        TestScopeChildrenPackage beanViaAnnotation = context.getBean(TestScopeChildrenPackage.class);

        Assertions.assertNotNull(beanViaAnnotation);
    }

    @Test
    @DisplayName("@ComponentSacn 会扫描自身所在包的类")
    public void testComponentSacnPeerPackage() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfigurationn.class);
        // 通过 Bean 类型获取 Bean 实例。
        TestScopePeerPackage beanViaAnnotation = context.getBean(TestScopePeerPackage.class);

        Assertions.assertNotNull(beanViaAnnotation);
    }

    @Test
    @DisplayName("默认 Bean 的作用域是单实例")
    public void testBeanIsDefaultSingleton() {

        HelloBean bean1 = context.getBean(HelloBean.class);
        HelloBean bean2 = context.getBean(HelloBean.class);
        if (bean1 == bean2) {
            System.out.println("bean1==bean2");
        }
        Assertions.assertEquals(bean1, bean2);
    }

    @Test
    @DisplayName("期望通过@ComponentScan得到 Bean 是单例")
    public void testBeanViaComponentScanIsSingleton() throws InterruptedException {
        System.out.println("=========期望通过@ComponentScan得到 Bean 是单例===========");
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    HelloService service = context.getBean(HelloService.class);
                    HelloDao dao = context.getBean(HelloDao.class);
                    System.out.println(service.getHelloDao());
                    System.out.println(dao);
                    list1.add(service.getHelloDao().toString());
                    list2.add(dao.toString());
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Assertions.assertLinesMatch(list1, list2);
        };
        startTaskAllInOnce(5, task);

    }

    @AfterEach
    public void afterEach() {
        context.close();
    }

    private long startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for (int i = 0; i < threadNums; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            // 将结束门减1，减到0时，就可以开启结束门了
                            endGate.countDown();
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        long endTime = System.nanoTime();
        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
        return endTime - startTime;
    }
}