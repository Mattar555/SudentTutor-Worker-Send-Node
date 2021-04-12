package com.hmses.demo.service.graph.enumerations;

public enum ScopeEnums {


    OPEN_ID("openid", ""),
    OFFLINE_ACCESS("offline_access", ""),
    PROFILE("profile", ""),
    USER_READ("User.Read", ""),
    MAIL_READ("Mail.Read", ""),
    MAIL_READWRITE("Mail.ReadWrite", ""),
    MAIL_SEND("Mail.Send", "")
    ;

    ScopeEnums(String scopeType, String scopeDescription) {
        this.scopeType = scopeType;
        this.scopeDescription = scopeDescription;
    }

    private static final String DELIMITER = " ";

    private String scopeType;
    private String scopeDescription;

    public String getScopeType() {
        return scopeType;
    }

    public String getScopeDescription() {
        return scopeDescription;
    }
}
