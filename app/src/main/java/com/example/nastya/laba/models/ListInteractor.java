package com.example.nastya.laba.models;

import com.example.nastya.laba.entity.children.Children;

import java.util.ArrayList;

public interface ListInteractor {
    interface OnFinishedListener {
        void onFinishedLoad(ArrayList<Children> hitArrayList);

        void onFinishedReload(ArrayList<Children> hitArrayList);

        void onFailure(Throwable t);
    }

    void getHitArrayList(ListInteractor.OnFinishedListener onFinishedListener, boolean dataChanged);
}