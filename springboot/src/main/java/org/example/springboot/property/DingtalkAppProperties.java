package org.example.springboot.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉应用配置信息
 * <p>创建时间: 2020/8/1 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Component
@ConfigurationProperties(value = DingtalkAppProperties.PREFIX)
public class DingtalkAppProperties {
    public static final String PREFIX = "dingtalk";

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    private String corpId;

    public Map<String, CorpH5> getCorph5() {
        return corph5;
    }

    public Map<String, Corpeapp> getCorpeapp() {
        return corpeapp;
    }

    public Map<String, LoginMan> getLoginman() {
        return loginman;
    }

    private final Map<String, CorpH5> corph5 = new HashMap<>();
    private final Map<String, Corpeapp> corpeapp = new HashMap<>();
    private final Map<String, LoginMan> loginman = new HashMap<>();

    /**
     * H5 微应用
     */
    @Data
    public static class CorpH5 {
        private Integer agentId;
        private String appKey;
        private String appSecret;
    }

    /**
     * 小程序
     */
    @Data
    public static class Corpeapp {
        private Integer agentId;
        private String appKey;
        private String appSecret;
    }

    /**
     * 登录应用
     */
    @Data
    public static class LoginMan {
        private String appId;
        private String appSecret;
    }
}
