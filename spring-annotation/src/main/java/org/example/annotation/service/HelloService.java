package org.example.annotation.service;

import org.example.annotation.dao.AppDao;
import org.example.annotation.dao.BookDao;
import org.example.annotation.dao.HelloDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Service
public class HelloService {

    // 可以通过 `@Qualifier` 显式让容器装配指定 ID 的 Bean
    @Qualifier("helloDao")
    @Autowired
    private HelloDao helloDao2;

    @Qualifier("appDao2")
    @Resource
    private AppDao appDao;

    //    @Resource(name = "appDao")
//    private AppDao appDao2;
    @Qualifier("appDao")
    @Inject
    private AppDao appDao2;

    @Inject
    private BookDao bookDao;

    @Qualifier("helloDao2")
    @Inject
    private HelloDao helloDao3;

    @Qualifier("helloDao4")
    @Autowired(required = false)
    private HelloDao helloDao4;

    public HelloDao getHelloDao() {
        return helloDao2;
    }

    @Override
    public String toString() {
        return "HelloService{" +
                "helloDao2=" + helloDao2 +
                ", appDao=" + appDao +
                ", appDao2=" + appDao2 +
                ", bookDao=" + bookDao +
                ", helloDao3=" + helloDao3 +
                ", helloDao4=" + helloDao4 +
                '}';
    }
}
