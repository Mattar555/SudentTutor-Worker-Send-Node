package com.hmses.demo.service.graph.enumerations;

public enum MessageReadEnums {

    FOLDER("inbox", ""),
    SORT("receivedDateTime DESC", ""),
    PROPERTIES("receivedDateTime,from,isRead,subject,bodyPreview", ""),
    MAX_RESULTS("10", "")
    ;

    MessageReadEnums(String value, String description) {
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
