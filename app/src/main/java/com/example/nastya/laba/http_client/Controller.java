package com.example.nastya.laba.http_client;

import com.example.nastya.laba.R;
import com.example.nastya.laba.activities.MainActivity;
import com.example.nastya.laba.model.Feed;
import com.example.nastya.laba.model.children.Children;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback <Feed> {

    @BindView(R.id.no_data)
    ImageView noData ;
    private final static String BASE_URL = "https://reddit.com/";
    private final static String TAG = "Controller";

    public final void callRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS)
                .build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RedditApi redditApi = retrofit.create(RedditApi.class);
        final Call <Feed> call = redditApi.getData();
        call.enqueue(this);
    }

    @Override
    public final void onResponse(@NonNull Call <Feed> call, @NonNull Response <Feed> response) {
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
    public final void onFailure(@NonNull Call <Feed> call, @NonNull Throwable t) {
        Log.e("onFailure", t.getMessage());
        noData.setVisibility(View.VISIBLE);
    }
}