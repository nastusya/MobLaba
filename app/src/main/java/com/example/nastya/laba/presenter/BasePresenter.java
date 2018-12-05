package com.example.nastya.laba.presenter;

import android.app.Activity;

import com.example.nastya.laba.ApplicationEx;

public class BasePresenter {
    protected ApplicationEx getApplicationInstance(Activity activity) {
        ApplicationEx mApplication = (ApplicationEx) activity.getApplication();
        return mApplication;
    }
}
