package com.example.nastya.laba.http_client;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient extends Application {
    private static RedditApi redditApi;

    @Override
    public void onCreate(){
        setRedditApi();
        super.onCreate();
    }

    public void setRedditApi(){
        String BASE_URL = "https://reddit.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        redditApi = retrofit.create(RedditApi.class);
    }

    public static RedditApi getRedditApi() {
        return redditApi;
    }
}