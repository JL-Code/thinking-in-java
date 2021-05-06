package org.example.mnc.sms;

import org.example.mnc.ChannelProvider;
import org.example.mnc.Notification;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Component
public class SmsChannelProvider implements ChannelProvider {

    @Override
    public Optional<Object> dispatch(Notification notification) {
        SmsNotification smsn = (SmsNotification) notification;
        // 做消息的发送准备
        SmsMessage message = smsn.getMessage();
        // 发送消息
        return Optional.of(message.getTemplateParam());
    }

    @Override
    public boolean supports(Notification notification) {
        return SmsNotification.class.equals(notification.getClass());
    }
}
