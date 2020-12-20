package org.example.beautiful.restapi.standard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class Problem {

    /**
     * 问题类型，默认值为 about:blank
     */
    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // 扩展字段
    private String path;

    public Problem() {
        type = "about:blank";
        title = "";
        detail = "";
        instance = "";
    }

    public Problem(String type, String title, int status, String detail, String instance) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return HttpStatus.valueOf(status);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

}
