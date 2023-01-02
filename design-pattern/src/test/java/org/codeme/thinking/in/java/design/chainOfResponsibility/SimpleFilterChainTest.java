package org.codeme.thinking.in.java.design.chainOfResponsibility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class SimpleFilterChainTest {

    @Test
    public void testFilterChain() {
        DefaultSecurityFilterChain filterChain1 = new DefaultSecurityFilterChain();
        DefaultSecurityFilterChain filterChain2 = new DefaultSecurityFilterChain();

        filterChain1.addFilter(new UrlFilter());
        filterChain1.addFilter(new SQLFilter());
        filterChain2.addFilter(new SensitiveWordsFilter());
        filterChain2.addFilter(new EmojiFilter());

        filterChain1.addFilter(filterChain2);

        Message message = new Message("create table 996 美国媒体：这是武汉病毒，哈哈。http://facebook.com");

        filterChain1.doFilter(message);

        System.out.println(message.getContent());

        Assertions.assertEquals(" 995 美国媒体：这是美国病毒，😀。facebook.com", message.getContent());
    }
}