package com.example.nastya.laba.presenter;

import android.app.Activity;

import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.models.DetailInteractor;
import com.example.nastya.laba.views.DetailsView;

public class DetailPresenterImpl extends BasePresenter
        implements DetailPresenter, DetailInteractor.OnFinishedListener {

    private DetailsView view;
    private Children children;

    public DetailPresenterImpl(DetailsView view) {
        this.view = view;
    }

    public void onAdd() {
        view.addToFavourite();
    }

    public void onRemove() {
        view.removeFromFavourite();
    }

    public void checkFavourite(Activity activity) {
        getApplicationInstance(activity).getDetailsInteractor().
                doFavourite(children, this);
    }

    public void favouriteResult(boolean favourite) {
        view.markFavourite(favourite);
    }

    public void getData(Activity activity) {
        children = getApplicationInstance(activity).getFragmentHandler().getArguments();
        view.setItems(children);
        getApplicationInstance(activity).getDetailsInteractor().isFavourite(children,
                this);
    }
}