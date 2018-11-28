package com.example.nastya.laba.model;

import android.content.SharedPreferences;

import com.example.nastya.laba.MVPInterfaces.DetailsContract;
import com.example.nastya.laba.entity.children.Children;
import com.google.gson.Gson;

public class DetailsModel implements DetailsContract.Model {
    private SharedPreferences sharedPreferences;

    public DetailsModel(SharedPreferences preferences) {
        this.sharedPreferences = preferences;

    }

    @Override
    public void setFavourite(Children children) {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        if (checkFavourite(children)) {
            prefEditor.remove(children.getData().getSubreddit());
            prefEditor.apply();
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(children);
            prefEditor.putString(children.getData().getSubreddit(), json);
            prefEditor.apply();
        }
    }

    @Override
    public boolean checkFavourite(Children children) {
        return sharedPreferences.contains(children.getData().getSubreddit());
    }
}
