package com.example.nastya.laba.presenter;

import android.app.Activity;

import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.fragments.FavouriteFragment;
import com.example.nastya.laba.models.ListInteractor;
import com.example.nastya.laba.views.ListView;

import java.util.ArrayList;

public class ListPrisenterImpl extends BasePresenter implements ListPresenter,
        ListInteractor.OnFinishedListener {

    private ListView view;

    public ListPrisenterImpl(ListView listView) {
        this.view = listView;
    }

    @Override
    public void requestDataFromServer(Activity activity) {
        getApplicationInstance(activity).getMainInteractor()
                .getHitArrayList(this, false);
    }

    @Override
    public void updateDataFromServer(Activity activity) {
        getApplicationInstance(activity).getMainInteractor()
                .getHitArrayList(this, true);
    }

    @Override
    public void onFinishedLoad(ArrayList <Children> childrenArrayList) {
        if (view != null) {
            view.setDataToRecyclerView(childrenArrayList);
        }
    }

    @Override
    public void onFinishedReload(ArrayList <Children> childrenArrayList) {
        if (view != null) {
            view.refreshData(childrenArrayList);
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (view != null) {
            view.onResponseFailure(throwable);
        }
    }

    @Override
    public void goToFavourites(Activity activity) {
        getApplicationInstance(activity).getFragmentHandler().setFragment(new FavouriteFragment());
    }
}