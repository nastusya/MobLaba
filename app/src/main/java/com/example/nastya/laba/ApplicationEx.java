package com.example.nastya.laba;

import android.app.Application;

import com.example.nastya.laba.http_client.RedditApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {

    private static final String ROOT_URL = "https://reddit.com/";

    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RedditApi getApiService() {
        return getRetrofitInstance().create(RedditApi.class);
    }
}