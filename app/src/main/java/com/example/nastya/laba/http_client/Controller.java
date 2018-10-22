package com.example.nastya.laba.http_client;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.nastya.laba.activities.MainActivity;
import com.example.nastya.laba.model.Feed;
import com.example.nastya.laba.model.children.Children;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Controller implements Callback <Feed> {
    private final static String TAG = "Controller";
    ImageView noData;

    @Override
    public void onResponse(Call <Feed> call, Response <Feed> response) {
        Log.d(TAG, "onResponse: Server Response: " + response.toString());
        Log.d(TAG, "onResponse: received information: " +
                Objects.requireNonNull(response.body()).toString());
        if (response.isSuccessful()) {
            noData.setVisibility(View.GONE);
            final ArrayList <Children> children = response.body().getData().getChildren();
            final MainActivity mainActivity = new MainActivity();
            mainActivity.drawData(children);
        } else {
            Log.i("data", "no data");
            noData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFailure(Call <Feed> call, Throwable t) {
        Log.e("onFailure", t.getMessage());
        noData.setVisibility(View.VISIBLE);
    }
}