package org.codeme.thinking.in.java.design.chainOfResponsibility.servlet;

/**
 * <p>创建时间: 2020/7/11 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class HttpRequest {
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HttpRequest(String body) {
        this.body = body;
    }

    private String body;
}
