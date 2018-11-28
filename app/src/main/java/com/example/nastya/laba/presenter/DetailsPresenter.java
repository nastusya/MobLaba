package com.example.nastya.laba.presenter;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.MVPInterfaces.DetailsContract;
import com.example.nastya.laba.entity.children.Children;

public class DetailsPresenter extends PresenterBase <DetailsContract.View>
        implements DetailsContract.Presenter {

    private DetailsContract.Model model;

    public DetailsPresenter(final ApplicationEx applicationEx) {
        super(applicationEx);
        model = applicationEx.getDetailsModel();
    }

    @Override
    public void onResume() {
        Children children = getCurrentChildren();
        model.setFavourite(children);
        view.displayChildren(children, model.checkFavourite(children));

    }

    @Override
    public void makeFavourite() {
        Children children = getCurrentChildren();
        model.setFavourite(children);
        view.displayChildren(children, model.checkFavourite(children));
    }

    @Override
    public Children getCurrentChildren() {
        return null;
    }

    @Override
    public void attachView(DetailsContract.View view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
