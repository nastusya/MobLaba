package com.example.nastya.laba.http_client;

import com.example.nastya.laba.entity.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RedditApi {

    @Headers("Content-Type: application/json")
    @GET(".json")
    Call<Feed> getData();
}