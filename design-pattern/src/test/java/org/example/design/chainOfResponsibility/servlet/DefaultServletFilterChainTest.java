package org.example.design.chainOfResponsibility.servlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class DefaultServletFilterChainTest {

    @Test
    @DisplayName("通过 ServletFilter不调用doFilter 终止 ServletFilterChain")
    public void testServletFilterChainOfRecursiveTermination() {
        HttpRequest request = new HttpRequest("request");
        HttpResponse response = new HttpResponse("response");
        DefaultServletFilterChain chain = new DefaultServletFilterChain();

        chain.addFilter(new AuthFilter());
        chain.addFilter(new DoNotCallDoFilterFilter());
        chain.addFilter(new TestFilter());

        chain.doFilter(request, response, chain);

        System.out.println(request.getBody());
        System.out.println(response.getBody());

        Assertions.assertEquals("authFilter", request.getBody());
        Assertions.assertEquals("authFilter", response.getBody());
    }

    @Test
    public void testServletFilterChain() {
        HttpRequest request = new HttpRequest("request");
        HttpResponse response = new HttpResponse("response");
        DefaultServletFilterChain chain = new DefaultServletFilterChain();
        DefaultServletFilterChain chain2 = new DefaultServletFilterChain();

        chain.addFilter(new AuthFilter());
        chain.addFilter(new TestFilter());

        chain2.addFilter(new Test2Filter());
        chain2.addFilter(new TestFilter());

        chain.addFilter(chain2);

        chain.doFilter(request, response, chain);

        System.out.println(request.getBody());
        System.out.println(response.getBody());

        Assertions.assertEquals("TestFilter", request.getBody());
        Assertions.assertEquals("authFilter", response.getBody());
    }
}