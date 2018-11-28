package com.example.nastya.laba.model;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.MVPInterfaces.ListContract;
import com.example.nastya.laba.entity.Feed;
import com.example.nastya.laba.entity.children.Children;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListModel implements ListContract.Model {
    private ArrayList <Children> childrenArrayList;

    @Override
    public ArrayList <Children> getChildList() {
        ApplicationEx.getApiService().getData().enqueue(new Callback <Feed>() {
            @Override
            public void onResponse(Call <Feed> call, Response <Feed> response) {
                if (response.body() != null) {
                    childrenArrayList = response.body().getData().getChildren();
                } else {
                    childrenArrayList = null;
                }
            }

            @Override
            public void onFailure(Call <Feed> call, Throwable t) {
                childrenArrayList = null;
            }
        });
        return childrenArrayList;
    }
}
