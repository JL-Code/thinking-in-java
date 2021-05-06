package org.example.mnc.wechat;

import org.example.mnc.ChannelProvider;
import org.example.mnc.Notification;
import org.example.mnc.wechat.message.BaseMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Component
public class WeChatChannelProvider implements ChannelProvider {

    @Override
    public Optional<Object> dispatch(Notification notification) {
        WeChatNotification weChatNotification = (WeChatNotification) notification;
        // 做消息的发送准备
        BaseMessage message = weChatNotification.getMessage();
        // 发送消息
        // 返回值
        return Optional.of(message.getMsgType());
    }

    @Override
    public boolean supports(Notification notification) {
        return WeChatNotification.class.equals(notification.getClass());
    }
}
