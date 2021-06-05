package org.example.mnc.wechat.message;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class TextMessage extends BaseMessage {
    private Text text;

    @Override
    public String getMsgType() {
        return "text";
    }
}
