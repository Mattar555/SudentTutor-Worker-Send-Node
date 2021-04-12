package com.hmses.demo.domain.graph;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String id;
    private Date receivedDateTime;
    private Recipient from;
    private Boolean isRead;
    private String subject;
    private String bodyPreview;
    private List<Recipient> toRecipients;
    private Body body;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getReceivedDateTime() {
        return receivedDateTime;
    }
    public void setReceivedDateTime(Date receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }
    public Recipient getFrom() {
        return from;
    }
    public void setFrom(Recipient from) {
        this.from = from;
    }
    public Boolean getIsRead() {
        return isRead;
    }
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getBodyPreview() {
        return bodyPreview;
    }
    public void setBodyPreview(String bodyPreview) {
        this.bodyPreview = bodyPreview;
    }

    public List<Recipient> getRecipients() {
        return toRecipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.toRecipients = recipients;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
