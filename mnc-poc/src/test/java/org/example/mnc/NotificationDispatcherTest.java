package org.example.mnc;

import org.example.mnc.sms.SmsChannelProvider;
import org.example.mnc.sms.SmsMessage;
import org.example.mnc.sms.SmsNotification;
import org.example.mnc.wechat.WeChatChannelProvider;
import org.example.mnc.wechat.WeChatNotification;
import org.example.mnc.wechat.message.TextMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
class NotificationDispatcherTest {

    static NotificationDispatcher<Notification> dispatcher = new DefaultNotificationDispatcher();

    @BeforeAll
    public static void initialize() {
        dispatcher.setChannelProviders(Arrays.asList(new SmsChannelProvider(), new WeChatChannelProvider()));
    }


    @Test
    void dispatch_Sms_Success() {

        // Given
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setTemplateParam("{\"code\":\"111\"}");
        SmsNotification smsNotification = new SmsNotification();
        smsNotification.setMessage(smsMessage);

        // When
        Optional<Object> reuslt = dispatcher.dispatch(smsNotification);

        // Then
        assertTrue(reuslt.isPresent());
        assertNotNull(reuslt.get());

        System.out.println(reuslt.get());
    }

    @Test
    void dispatch_WeChat_Success() {

        // Given
        TextMessage message = new TextMessage();
        WeChatNotification weChatNotification = new WeChatNotification();
        weChatNotification.setMessage(message);

        // When
        Optional<Object> reuslt = dispatcher.dispatch(weChatNotification);

        // Then
        assertTrue(reuslt.isPresent());
        assertNotNull(reuslt.get());

        System.out.println(reuslt.get());
    }
}