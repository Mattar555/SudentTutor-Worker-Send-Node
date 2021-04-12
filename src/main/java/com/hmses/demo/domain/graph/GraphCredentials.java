package com.hmses.demo.domain.graph;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GraphCredentials {

    @Id
    private String id;

    private String clientId;

    private String redirectURI;

    private String clientSecret;

    private String tenantId;

    // The front-end will have to pass me an empty TokenResponse when the above three parameters are passed
    private TokenResponse tokenResponse = new TokenResponse();

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public TokenResponse getTokenResponse() {
        return tokenResponse;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public void setTokenResponse(TokenResponse tokenResponse) {
        this.tokenResponse = tokenResponse;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
