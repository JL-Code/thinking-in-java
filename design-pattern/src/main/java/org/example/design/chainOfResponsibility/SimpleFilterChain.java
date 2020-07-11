package org.example.design.chainOfResponsibility;

import java.util.List;

/**
 * 简单的过滤链实现,用于存储的过滤器，通过操作一组或多组过滤器，来完成某个特定的任务。
 * 过滤器的启动、运行、停止都由过滤链负责维护。
 * <p>
 * OOP: 继承、封装、多态
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface SimpleFilterChain extends SimpleFilter {

    List<SimpleFilter> getFilters();

    /**
     * 添加一个过滤器到过滤链条中。
     *
     * @param filter 一个简单的过滤器实现类实例
     */
    public void addFilter(SimpleFilter filter);
}
