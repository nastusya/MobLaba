package com.example.nastya.laba.models;

import com.example.nastya.laba.entity.Feed;
import com.example.nastya.laba.http_client.RedditApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListInteractorImpl implements ListInteractor {

    private RedditApi apiService;

    public ListInteractorImpl(RedditApi apiService){
        this.apiService = apiService;
    }

    @Override
    public void getHitArrayList(final OnFinishedListener onFinishedListener,
                                final boolean dataChanged) {
        Call<Feed> call = apiService.getData();
        call.clone().enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if(!dataChanged) {
                    onFinishedListener.onFinishedLoad(response.body().getData().getChildren());
                }else {
                    onFinishedListener.onFinishedReload(response.body().getData().getChildren());
                }
            }
            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}