package org.example.mnc;

import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.Optional;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface NotificationDispatcher<T extends Notification> extends ApplicationContextAware {

    /**
     * 发送通知
     *
     * @param notification 通知
     * @return 通知结果
     */
    Optional<Object> dispatch(T notification);

    /**
     * 设置通知渠道集合
     *
     * @param providers 通知渠道集合
     */
    void setChannelProviders(Collection<ChannelProvider> providers);
}
