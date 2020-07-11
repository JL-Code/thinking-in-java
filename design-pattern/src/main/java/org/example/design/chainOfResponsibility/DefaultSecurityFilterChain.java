package org.example.design.chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class DefaultSecurityFilterChain implements SimpleFilterChain {

    private List<SimpleFilter> filters = new ArrayList<>();

    @Override
    public List<SimpleFilter> getFilters() {
        return filters;
    }

    /**
     * 添加一个过滤器到过滤链条中。
     *
     * @param filter 一个简单的过滤器实现类实例
     */
    public void addFilter(SimpleFilter filter) {
        filters.add(filter);
    }

    @Override
    public boolean doFilter(Message message) {
        // 迭代执行。
        for (SimpleFilter filter : filters) {
            boolean status = filter.doFilter(message);
            if (status == false) {
                // 退出过滤链
                System.out.println(filter + " 终止了过滤链！");
                break;
            }
        }
        return true;
    }
}
