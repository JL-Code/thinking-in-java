package org.example.mnc.sms;

import org.example.mnc.Message;

import java.util.List;

/**
 * <p>创建时间: 2021/5/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class SmsMessage implements Message {
    /**
     * 短信模板编码（ID）
     */
    private String templateCode;
    /**
     * 短信签名
     */
    private String signName;
    /**
     * 接收短信的手机号码。
     */
    private List<String> phoneNumbers;
    /**
     * 短信模板参数 (JSON 格式)
     * eg: {"code":"111"}
     */
    private String templateParam;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }
}
