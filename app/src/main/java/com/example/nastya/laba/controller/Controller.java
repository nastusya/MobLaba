package com.example.nastya.laba.controller;

import com.example.nastya.laba.interfaces.RedditApi;
import com.example.nastya.laba.model.Feed;
import com.example.nastya.laba.model.children.Children;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback <Feed> {

    private final static String BASE_URL = "https://reddit.com/";
    private final static String TAG = "Controller";


    public void start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RedditApi redditApi = retrofit.create(RedditApi.class);
        Call <Feed> call = redditApi.getData();
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call <Feed> call, @NonNull Response <Feed> response) {
        Log.d(TAG, "onResponse: Server Response: " + response.toString());
        Log.d(TAG, "onResponse: received information: " +
                Objects.requireNonNull(response.body()).toString());
        ArrayList <Children> children = response.body().getData().getChildren();
        displayData(children);
    }

    @Override
    public void onFailure(@NonNull Call <Feed> call, @NonNull Throwable t) {
        Log.e("onFailure", t.getMessage());
    }

    private void displayData(ArrayList <Children> children) {
        for (int i = 0; i < children.size(); i++) {
            Log.d("Data", children.get(i).toString() +
                    "\n--------------------------------------");
        }
    }
}
