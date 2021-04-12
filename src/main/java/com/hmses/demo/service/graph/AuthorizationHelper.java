package com.hmses.demo.service.graph;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hmses.demo.domain.graph.GraphCredentials;
import com.hmses.demo.domain.graph.TokenResponse;
import com.hmses.demo.service.graph.enumerations.GraphEnums;
import com.hmses.demo.service.graph.enumerations.RegistrationUrlEnums;
import com.hmses.demo.service.graph.enumerations.ScopeEnums;
import org.springframework.web.util.UriComponentsBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class AuthorizationHelper {

    private static final String DELIMITER = " ";


    private static String getScopes() {
        List<String> desiredScopes =  Stream.of(ScopeEnums.values())
                .map(ScopeEnums::getScopeType)
                .collect(Collectors.toList());
        return String.join(DELIMITER, desiredScopes);
    }


    public static String getLoginUrl(String applicationId,
                                     String redirectUrl,
                                     UUID state,
                                     UUID nonce) {

        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(GraphEnums.AUTHORIZATION_URL.value);
        urlBuilder.queryParam(
                RegistrationUrlEnums.CLIENT_ID.getHeaderName(),
                applicationId
        );
        urlBuilder.queryParam(
                RegistrationUrlEnums.REDIRECT_URI.getHeaderName(),
                redirectUrl)
        ;
        urlBuilder.queryParam(
                RegistrationUrlEnums.RESPONSE_TYPE.getHeaderName(),
                RegistrationUrlEnums.RESPONSE_TYPE.getHeaderValue()
        );
        urlBuilder.queryParam(
                RegistrationUrlEnums.SCOPE.getHeaderName(),
                getScopes()
        );
        urlBuilder.queryParam(
                RegistrationUrlEnums.STATE.getHeaderName(),
                state
        );
        urlBuilder.queryParam(
                RegistrationUrlEnums.NONCE.getHeaderName(),
                nonce
        );
        urlBuilder.queryParam(
                RegistrationUrlEnums.RESPONSE_MODE.getHeaderName(),
                RegistrationUrlEnums.RESPONSE_MODE.getHeaderValue()
        );

        return urlBuilder.toUriString();
    }

    public static TokenResponse getTokenFromAuthCode(String authCode,
                                                     GraphCredentials graphCredentials) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GraphEnums.AUTHORITY.value)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        TokenService tokenService = retrofit.create(TokenService.class);

        try {
            return tokenService.getAccessTokenFromAuthCode(
                    graphCredentials.getTenantId(),
                    graphCredentials.getClientId(),
                    graphCredentials.getClientSecret(),
                    GraphEnums.AUTHORIZATION_FLOW.value,
                    authCode,
                    graphCredentials.getRedirectURI())
                    .execute()
                    .body();
        } catch (IOException e) {
            TokenResponse error = new TokenResponse();
            error.setError("IOException");
            error.setErrorDescription(e.getMessage());
            return error;
        }
    }

    public static TokenResponse refreshToken(GraphCredentials graphCredentials) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GraphEnums.AUTHORITY.value)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        TokenService tokenService = retrofit.create(TokenService.class);

        try {
            return tokenService.getAccessTokenFromRefreshToken(
                    graphCredentials.getTenantId(),
                    graphCredentials.getClientId(),
                    graphCredentials.getClientSecret(),
                    GraphEnums.REFRESH_FLOW.value,
                    graphCredentials.getTokenResponse().getRefreshToken(),
                    graphCredentials.getRedirectURI())
                    .execute()
                    .body();
        } catch (IOException e) {
            TokenResponse error = new TokenResponse();
            error.setError("IOException");
            error.setErrorDescription(e.getMessage());
            return error;
        }
    }

}
