package com.hmses.demo.service.graph.enumerations;

public enum GraphEnums {

    AUTHORIZATION_FLOW("authorization_code",
            "Authorization flow as described in: https://docs.microsoft.com/en-us/graph/auth-v2-user"),
    REFRESH_FLOW("refresh_token",
            "Refresh flow as described in: https://docs.microsoft.com/en-us/graph/auth-v2-user"),
    AUTHORITY("https://login.microsoftonline.com", ""),
    AUTHORIZATION_URL("https://login.microsoftonline.com/common/oauth/v2.0/authorize", ""),
    MICROSOFT_BASE_URL("https://graph.microsoft.com", "")
    ;

    GraphEnums(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String value;
    public String description;

}
