package com.example.nastya.laba.models;

import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.repositories.SharedPreferences;

public class DetailInterctorImpl implements DetailInteractor {

    SharedPreferences preferences;

    public DetailInterctorImpl(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void doFavourite(Children children, OnFinishedListener onFinishedListener) {
        if (!preferences.contains(children.getData().getSubreddit())) {
            preferences.add(children);
            onFinishedListener.onAdd();
        } else {
            preferences.remove(children);
            onFinishedListener.onRemove();
        }
    }

    @Override
    public void isFavourite(Children children, OnFinishedListener onFinishedListener) {
        if (preferences.contains(children.getData().getSubreddit())) {
            onFinishedListener.favouriteResult(true);
        } else {
            onFinishedListener.favouriteResult(false);
        }
    }
}

