package com.example.nastya.laba.presenter;

import android.app.Activity;

import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.models.FavInteractor;
import com.example.nastya.laba.views.FavouritesView;

import java.util.ArrayList;

public class FavPresenterImpl extends BasePresenter implements FavPresenter,
        FavInteractor.OnFinishedListener {

    FavouritesView view;

    public FavPresenterImpl(FavouritesView view) {
        this.view = view;
    }

    public void addData(ArrayList <Children> children) {
        view.setDataToRecyclerView(children);
    }

    public void requestDataFromStorage(Activity activity) {
        getApplicationInstance(activity).getFavInteractor().
                getHitArrayList(this);
    }
}
