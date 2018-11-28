package com.example.nastya.laba.presenter;

import com.example.nastya.laba.ApplicationEx;

public abstract class PresenterBase<View> {
    View view;
    ApplicationEx applicationEx;

    PresenterBase(ApplicationEx applicationEx) {

    }

    public void attachView(View view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    public abstract void onResume();
}
