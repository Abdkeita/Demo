package com.example.abd.intermediate_challenge.api;


import com.example.abd.intermediate_challenge.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by abd on 9/14/2017.
 */
// End-point of the application
public interface Service {
    @GET("/search/users?q=language:java+location:lagos")
    Call<ItemResponse> getItems();

}
