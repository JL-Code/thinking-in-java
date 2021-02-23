package org.example.redis.model;

/**
 * <p>创建时间: 2021/2/4 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public enum LoginEndEnum {
    /**
     * 应用平台
     */
    APP_PLATFORM("app-platform"),
    /**
     * 经纪人端
     */
    AGENT_END("agent"),
    /**
     * 运营端
     */
    OPS_END("ops");

    private final String value;

    LoginEndEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
