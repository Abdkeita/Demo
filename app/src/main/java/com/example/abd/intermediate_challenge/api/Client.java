package com.example.abd.intermediate_challenge.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abd on 9/14/2017.
 */
// this is the base url of the application, it will fetch all the information throught this API.
public class Client {
    public static final String BASE_URL = "https://api.github.com";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
