package com.hmses.demo.service.graph;


import com.hmses.demo.domain.graph.Message;
import com.hmses.demo.domain.graph.OutlookUser;
import com.hmses.demo.domain.graph.PagedResult;
import retrofit2.Call;
import retrofit2.http.*;

public interface OutlookService {

    @GET("/v1.0/me")
    Call<OutlookUser> getCurrentUser();

    @GET("/v1.0/me/mailfolders/{folderid}/messages")
    Call<PagedResult<Message>> getMessages(
            @Path("folderid") String folderId,
            @Query("$orderby") String orderBy,
            @Query("$select") String select,
            @Query("$top") Integer maxResults
    );

    @POST("/v1.0/me/sendMail")
    Call<Message> sendMessage(@Body Message message);
}
