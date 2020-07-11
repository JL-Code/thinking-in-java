package org.example.design.chainOfResponsibility.servlet;

import java.util.List;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class FilterChainProxy {
    private List<ServletFilterChain> filterChains;
}
