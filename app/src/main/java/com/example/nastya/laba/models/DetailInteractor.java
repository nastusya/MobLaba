package com.example.nastya.laba.models;

import com.example.nastya.laba.entity.children.Children;

public interface DetailInteractor {

    interface OnFinishedListener {
        void onAdd();
        void onRemove();
        void favouriteResult(boolean change);
    }

    void doFavourite(Children children, DetailInteractor.OnFinishedListener onFinishedListener);
    void isFavourite(Children children, DetailInteractor.OnFinishedListener onFinishedListener);
}
