package org.example.annotation.bean.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageBean {

    @Autowired
    CircularReferenceBean circularReferenceBean;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content = "message init";
}
