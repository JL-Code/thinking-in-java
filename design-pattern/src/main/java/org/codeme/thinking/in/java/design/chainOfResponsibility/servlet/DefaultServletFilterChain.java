package org.codeme.thinking.in.java.design.chainOfResponsibility.servlet;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class DefaultServletFilterChain implements ServletFilterChain {

    private int currentPosition = 0;
    private List<ServletFilter> filters = new ArrayList<>();


    @Override
    public List<ServletFilter> getFilters() {
        return filters;
    }

    /**
     * 添加一个过滤器到过滤链条中。
     *
     * @param filter 一个简单的过滤器实现类实例
     */
    @Override
    public void addFilter(ServletFilter filter) {
        filters.add(filter);
    }

    /**
     * 获取下一个过滤器
     *
     * @return
     */
    @Override
    public ServletFilter nextFilter() {
        System.out.println(this + "：" + currentPosition);
        if (currentPosition == filters.size()) {
            return null;
        }
        ServletFilter filter = filters.get(currentPosition);
        System.out.println("currentFilter：" + filter);
        currentPosition++;

        return filter;
    }

    @Override
    public void doFilter(HttpRequest request, HttpResponse response, ServletFilterChain chain) {

        ServletFilter filter = nextFilter();
        if (filter instanceof ServletFilterChain) {
            filter.doFilter(request, response, (ServletFilterChain) filter);
            return;
        }
        // 递归出口
        if (filter != null) {
            filter.doFilter(request, response, chain);
        }
    }
}
