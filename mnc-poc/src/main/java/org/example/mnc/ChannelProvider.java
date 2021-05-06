package org.example.mnc;

import java.util.Optional;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface ChannelProvider<T extends Notification> {
    Optional<Object> dispatch(T notification);
    boolean supports(T notification);
}
