package com.example.nastya.laba.models;

import com.example.nastya.laba.entity.children.Children;

import java.util.ArrayList;

public interface FavInteractor {
    interface OnFinishedListener {
    void addData(ArrayList<Children> hits);
}
    void getHitArrayList(FavInteractor.OnFinishedListener onFinishedListener);
}
