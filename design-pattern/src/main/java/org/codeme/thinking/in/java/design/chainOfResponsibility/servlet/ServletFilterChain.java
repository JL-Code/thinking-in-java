package org.codeme.thinking.in.java.design.chainOfResponsibility.servlet;

import java.util.List;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * filter 应该支持自定义执行顺序
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface ServletFilterChain extends ServletFilter {

    List<ServletFilter> getFilters();

    /**
     * 添加一个过滤器到过滤链条中。
     *
     * @param filter 一个简单的过滤器实现类实例
     */
    public void addFilter(ServletFilter filter);

    /**
     * 获取下一个过滤器
     *
     * @return
     */
    public ServletFilter nextFilter();
}
