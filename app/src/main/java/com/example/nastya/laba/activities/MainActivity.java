package com.example.nastya.laba.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nastya.laba.R;
import com.example.nastya.laba.adapters.RedditAdapter;
import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.model.Feed;
import com.example.nastya.laba.model.children.Children;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.list_photos)
    protected RecyclerView recyclerView;
    public ImageView noData;
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeContainer;
    RedditAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d("MainActivity", "Start main activity");
    }

    @Override
    protected void onResume() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeCall();
                swipeContainer.setRefreshing(false);
            }
        });
        makeCall();
        super.onResume();
    }

    public void makeCall() {
        Call <Feed> call = ((ApplicationEx) getApplication()).getApiService().getData();
        call.enqueue(new Callback <Feed>() {
            @Override
            public void onResponse(Call <Feed> call, Response <Feed> response) {
                Toast.makeText(MainActivity.this, R.string.successful_response,
                        Toast.LENGTH_LONG).show();
                if (response.body() == null) {
                    noData.setImageAlpha(R.drawable.ic_error);
                } else {
                    ArrayList <Children> hits = response.body().getData().getChildren();
                    setAdapter(hits);
                }
            }

            @Override
            public void onFailure(Call <Feed> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, R.string.unsuccessful_response
                        + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setAdapter(ArrayList <Children> data) {
        adapter = RedditAdapter.getRedditAdapter();
        adapter.loadData(data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}