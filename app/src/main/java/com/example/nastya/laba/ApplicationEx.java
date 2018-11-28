package com.example.nastya.laba;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.http_client.RedditApi;
import com.example.nastya.laba.model.DetailsModel;
import com.example.nastya.laba.model.FavModel;
import com.example.nastya.laba.model.ListModel;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {

    private Children children;
    private SharedPreferences preferences;
    private ListModel listModel;
    private DetailsModel detailsModel;
    private FavModel favoritesModel;
    private static final String ROOT_URL = "https://reddit.com/";
    public final static String FAVOURITE = "Favourite";

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getApplicationContext()
                .getSharedPreferences(FAVOURITE, Context.MODE_PRIVATE);

        detailsModel = new DetailsModel(getSharedPreferences());
        favoritesModel = new FavModel(getSharedPreferences());
        listModel = new ListModel();
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RedditApi getApiService() {
        return getRetrofitInstance().create(RedditApi.class);
    }

    public ListModel getListModel() {
        return listModel;
    }

    public DetailsModel getDetailsModel() {
        return detailsModel;
    }

    public FavModel getFavoritesModel() {
        return favoritesModel;
    }

    public Children getCurrentChildren() {
        return children;
    }

    public void setCurrentChildren(Children child) {
        children = child;
    }

    public SharedPreferences getSharedPreferences() {
        return preferences;
    }
}