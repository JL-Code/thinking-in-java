package org.example.mnc.sms;

import org.example.mnc.Notification;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class SmsNotification implements Notification<SmsMessage> {

    private final static String TYPE = "SMS";

    private SmsMessage message;

    @Override
    public SmsMessage getMessage() {
        return message;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public void setMessage(SmsMessage message) {
        this.message = message;
    }
}
