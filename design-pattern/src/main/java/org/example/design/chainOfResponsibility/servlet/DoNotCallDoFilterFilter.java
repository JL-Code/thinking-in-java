package org.example.design.chainOfResponsibility.servlet;

/**
 * 在 ServletFilterChain 中使用递归方法调用每个 Filter，执行doFilter方法，
 * 其中doFilter方法支持了递归调用，如果其中某个Filter不执行 doFilter 那么在这个Filter之后的
 * 所有 Filter 都将得不到调用，递归将会终止。
 *
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class DoNotCallDoFilterFilter implements ServletFilter {
    @Override
    public void doFilter(HttpRequest request, HttpResponse response, ServletFilterChain chain) {
        System.out.println(this + " 后续的Filter将不会被执行，递归调用在此终止！");
    }
}
