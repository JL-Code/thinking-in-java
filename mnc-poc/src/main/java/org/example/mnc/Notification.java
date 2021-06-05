package org.example.mnc;

import java.io.Serializable;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface Notification<T extends Message> extends Serializable {
    /**
     * 消息主体
     *
     * @return 消息主体
     */
    T getMessage();

    /**
     * 通知类型
     *
     * @return
     */
    String getType();
}
