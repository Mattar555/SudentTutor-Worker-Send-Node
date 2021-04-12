package com.hmses.demo.service.graph;

import java.io.IOException;
import java.util.UUID;

import com.hmses.demo.service.graph.enumerations.GraphEnums;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class OutlookServiceBuilder {

    public static OutlookService getOutlookService(String accessToken) {
        // Create a request interceptor to add headers that belong on
        // every request
        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Builder builder = original.newBuilder()
                        .header("User-Agent", "java-tutorial")
                        .header("client-request-id", UUID.randomUUID().toString())
                        .header("return-client-request-id", "true")
                        .header("Authorization", String.format("Bearer %s", accessToken))
                        .method(original.method(), original.body());

                Request request = builder.build();
                return chain.proceed(request);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build();

        // Create and configure the Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GraphEnums.MICROSOFT_BASE_URL.value)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        // Generate the token service
        return retrofit.create(OutlookService.class);
    }
}
