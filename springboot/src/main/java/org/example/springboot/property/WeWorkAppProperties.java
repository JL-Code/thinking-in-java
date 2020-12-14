package org.example.springboot.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信配置信息
 * <p>创建时间: 2020/8/1 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Component
@ConfigurationProperties(value = WeWorkAppProperties.PREFIX)
public class WeWorkAppProperties {
    public static final String PREFIX = "wechat.corp";

    private String corpId;
    private final Map<String, Corpapp> corpapp = new HashMap<>();

    public Map<String, Corpapp> getCorpapp() {
        return this.corpapp;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    @Data
    public static class Corpapp {
        private Integer agentId;
        private String secret;
        private String token;
        private String encodingAESKey;
    }
}
