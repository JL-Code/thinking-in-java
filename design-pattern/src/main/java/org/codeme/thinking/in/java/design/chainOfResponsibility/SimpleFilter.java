package org.codeme.thinking.in.java.design.chainOfResponsibility;

/**
 * 责任链模式：分而治之，职责分离。
 * 简单的过滤器接口，用于实现消息字符串的过滤。
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface SimpleFilter {

    /**
     * 🤔 需求：
     * 1. 在某个过滤器中终止整个过滤器链条执行过程（不执行后续过滤器）。
     *
     * @param message
     */
    boolean doFilter(Message message);
}

/**
 * 敏感词语过滤器
 */
class SensitiveWordsFilter implements SimpleFilter {

    @Override
    public boolean doFilter(Message message) {
        String msg = message.getContent();
        msg = msg.replace("996", "995");
        msg = msg.replace("武汉病毒", "美国病毒");
        message.setContent(msg);
        System.out.println(this);
        return true;
    }
}

/**
 * Emoji 图标过滤
 */
class EmojiFilter implements SimpleFilter {

    @Override
    public boolean doFilter(Message message) {
        String msg = message.getContent();
        msg = msg.replace("哈哈", "😀");
        msg = msg.replace("哭", "😭");
        message.setContent(msg);
        System.out.println(this);
        return true;
    }
}

class SQLFilter implements SimpleFilter {

    @Override
    public boolean doFilter(Message message) {
        String msg = message.getContent();
        msg = msg.replace("create table", "");
        message.setContent(msg);
        System.out.println(this);
        return true;
    }
}