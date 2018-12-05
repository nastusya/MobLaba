package com.example.nastya.laba.presenter;

import android.app.Activity;

public interface ListPresenter {
    void requestDataFromServer(Activity activity);

    void updateDataFromServer(Activity activity);

    void goToFavourites(Activity activity);
}
