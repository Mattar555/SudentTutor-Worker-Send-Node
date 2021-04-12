package com.hmses.demo.service.graph.enumerations;

public enum MessageSendEnums {

    SUBJECT("Confirmation of attendance", ""),
    BODY("Hello!! This is an automated email. " +
                 "Please confirm your attendance this weekend by replying with either YES or NO", ""),
    BODY_CONTENT("Text", ""),

    ;


    MessageSendEnums(String value, String description) {
        this.value = value;
        this.description = description;
    }

    private String value;
    private String description;

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

}
