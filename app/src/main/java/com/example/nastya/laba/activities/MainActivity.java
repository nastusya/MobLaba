package com.example.nastya.laba.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nastya.laba.R;
import com.example.nastya.laba.adapters.RedditAdapter;
import com.example.nastya.laba.http_client.Controller;
import com.example.nastya.laba.model.children.Children;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.list_photos)
    private RecyclerView recyclerView;
    private static RedditAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final Controller controller = new Controller();
        controller.callRetrofit();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        swipeContainer.setColorSchemeResources(R.color.colorPrimaryDark);
    }

    public void drawData(ArrayList <Children> data) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RedditAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    public final void refresh() {
        adapter.clear();
        final Controller controller = new Controller();
        controller.callRetrofit();
        swipeContainer.setRefreshing(false);
    }
}