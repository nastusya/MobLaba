package com.example.nastya.laba.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nastya.laba.entity.children.Children;
import com.google.gson.Gson;

import java.util.Map;

public class SharedPreferences {
    public static final String FAVOURITES = "favourites";

    private final android.content.SharedPreferences mPrefs;

    public SharedPreferences(Context context) {
        mPrefs = context.getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
    }

    public void add(Children hit){
        mPrefs.edit().putString(hit.getData().getSubreddit(), new Gson().toJson(hit)).commit();
    }

    public void remove(Children hit) {
        mPrefs.edit().remove(hit.getData().getSubreddit()).commit();
    }

    public boolean contains (String name) {
       if (mPrefs.contains(name)){
            return true;
        } else {
            return false;
        }
    }

    public Map<String, ?> getAll(){
        return mPrefs.getAll();
    }



}
