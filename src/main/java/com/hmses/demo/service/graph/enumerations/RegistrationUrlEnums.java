package com.hmses.demo.service.graph.enumerations;

public enum RegistrationUrlEnums {

    CLIENT_ID("client_id", ""),
    REDIRECT_URI("redirect_uri", ""),
    RESPONSE_TYPE("response_type", "code id_token"),
    SCOPE("scope", ""),
    STATE("state", ""),
    NONCE("nonce", ""),
    RESPONSE_MODE("response_mode", "form_post")
    ;
    RegistrationUrlEnums(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    private String headerName;
    private String headerValue;

    public String getHeaderName() {
        return headerName;
    }

    public String getHeaderValue() {
        return headerValue;
    }

}
