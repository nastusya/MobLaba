package com.example.nastya.laba.presenter;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.MVPInterfaces.ListContract;
import com.example.nastya.laba.entity.children.Children;

import java.util.ArrayList;

public class ListPresenter extends PresenterBase <ListContract.View>
        implements ListContract.Presenter {
    private ListContract.Model model;

    public ListPresenter(ApplicationEx applicationEx) {
        super(applicationEx);
        model = applicationEx.getListModel();
    }

    @Override
    public void onResume() {
        loadData();
    }

    @Override
    public void loadData() {
        ArrayList <Children> childrenArrayList = model.getChildList();
        if (childrenArrayList == null) {
            view.noData();
        } else {
            view.displayChildren(childrenArrayList);
        }

    }

    @Override
    public void seletedChild(Children children) {
        applicationEx.setCurrentChildren(children);
    }

    @Override
    public void attachView(ListContract.View view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
