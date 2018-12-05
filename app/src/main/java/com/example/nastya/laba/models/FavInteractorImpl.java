package com.example.nastya.laba.models;

import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.repositories.SharedPreferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class FavInteractorImpl implements FavInteractor {

    SharedPreferences preferences;

    public FavInteractorImpl(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void getHitArrayList(FavInteractor.OnFinishedListener onFinishedListener) {
        ArrayList<Children> children = new ArrayList<>();
        Map<String, ?> map = preferences.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            Children hit = new Gson().fromJson(entry.getValue().toString(), Children.class);
            children.add(hit);
        }
        onFinishedListener.addData(children);
    }
}

