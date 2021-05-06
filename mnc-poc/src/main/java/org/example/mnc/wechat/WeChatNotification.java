package org.example.mnc.wechat;

import org.example.mnc.Notification;
import org.example.mnc.wechat.message.BaseMessage;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class WeChatNotification implements Notification<BaseMessage> {
    private final static String NOTIFICATION_TYPE = "WECHAT";

    private BaseMessage message;

    @Override
    public BaseMessage getMessage() {
        return message;
    }

    @Override
    public String getType() {
        return NOTIFICATION_TYPE;
    }

    public void setMessage(BaseMessage message) {
        this.message = message;
    }
}
