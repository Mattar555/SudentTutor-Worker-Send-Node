package com.hmses.demo.domain.graph;

public class Body {

    public String contentType;
    public String content;

    public String getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
