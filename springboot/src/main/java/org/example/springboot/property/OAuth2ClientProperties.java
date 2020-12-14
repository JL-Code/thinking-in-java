package org.example.springboot.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>创建时间: 2020/8/3 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Component
@ConfigurationProperties(value = OAuth2ClientProperties.PREFIX)
public class OAuth2ClientProperties {
    public static final String PREFIX = "highzap.security.oauth2";

    private final Map<String, OAuth2ClientDetails> client = new HashMap<>();

    public Map<String, OAuth2ClientDetails> getClient() {
        return client;
    }

    @Data
    public static class OAuth2ClientDetails {
        private String clientId;
        private String clientSecret;
    }
}
