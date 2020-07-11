package org.example.design.chainOfResponsibility.servlet;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class TestFilter implements ServletFilter {
    @Override
    public void doFilter(HttpRequest request, HttpResponse response, ServletFilterChain chain) {
        request.setBody("TestFilter");

        chain.doFilter(request, response, chain);

        response.setBody("TestFilter");
    }
}
