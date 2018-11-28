package com.example.nastya.laba.model;

import android.content.SharedPreferences;

import com.example.nastya.laba.MVPInterfaces.FavContract;
import com.example.nastya.laba.entity.children.Children;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class FavModel implements FavContract.Model {
    private SharedPreferences sharedPreferences;

    public FavModel(SharedPreferences preferences) {
        this.sharedPreferences = preferences;
    }

    @Override
    public ArrayList <Children> getFavourite() {
        ArrayList <Children> childrenArrayList = new ArrayList <>();
        Map <String, ?> map = sharedPreferences.getAll();
        if (map != null) {
            for (Map.Entry <String, ?> entry : map.entrySet()) {
                final Children children;
                children = new Gson().
                        fromJson(entry.getValue().toString(), Children.class);
                childrenArrayList.add(children);
            }
        }
        return childrenArrayList;
    }
}