package org.example.mnc.wechat.message;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class ImageMessage extends BaseMessage {
    private Image image;

    @Override
    public String getMsgType() {
        return "image";
    }
}
