package org.codeme.thinking.in.java.design.chainOfResponsibility;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class UrlFilter implements SimpleFilter {
    @Override
    public boolean doFilter(Message message) {
        String msg = message.getContent();
        msg = msg.replace("http://", "");
        msg = msg.replace("https://", "");
        message.setContent(msg);
        System.out.println(this);
        return true;
    }
}
