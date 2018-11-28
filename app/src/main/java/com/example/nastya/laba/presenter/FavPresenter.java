package com.example.nastya.laba.presenter;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.MVPInterfaces.FavContract;
import com.example.nastya.laba.entity.children.Children;

import java.util.ArrayList;

public class FavPresenter extends PresenterBase <FavContract.View>
        implements FavContract.Presenter {
    FavContract.Model model;

    FavPresenter(ApplicationEx applicationEx) {
        super(applicationEx);
    }

    @Override
    public void loadData() {
        ArrayList <Children> childrenArrayList = model.getFavourite();
        view.displayFavourites(childrenArrayList);
    }

    @Override
    public void seletedChild(Children children) {

    }

    @Override
    public void attachView(FavContract.View view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onResume() {

    }
}
