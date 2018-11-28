package com.example.nastya.laba.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.R;
import com.example.nastya.laba.adapters.RedditAdapter;
import com.example.nastya.laba.entity.Feed;
import com.example.nastya.laba.entity.children.Children;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private boolean isChange = false;
    @BindView(R.id.list_photos)
    protected RecyclerView recyclerView;
    public ImageView noData;
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeContainer;
    RedditAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reddit_list, container, false);
        if (getActivity() != null) {
            ButterKnife.bind(this, view);
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    isChange = true;
                    makeCall();
                    swipeContainer.setRefreshing(false);
                }
            });
        }
        makeCall();
        return view;
    }

    public void makeCall() {
        Call <Feed> call = ApplicationEx.getApiService().getData();
        call.enqueue(new Callback <Feed>() {
            @Override
            public void onResponse(Call <Feed> call, Response <Feed> response) {
                Toast.makeText(getActivity(), R.string.successful_response,
                        Toast.LENGTH_LONG).show();
                if (response.body() == null) {
                    noData.setImageAlpha(R.drawable.ic_error);
                } else {
                    ArrayList <Children> children = response.body().getData().getChildren();
                    Log.e("ERROR", children.toString());
                    Toast.makeText(getContext(), children.toString(), Toast.LENGTH_LONG);
                    if (!isChange) {
                        setAdapter(children);
                    } else {
                        refreshData(children);
                    }
                }
            }

            @Override
            public void onFailure(Call <Feed> call, Throwable throwable) {
                Toast.makeText(getActivity(), R.string.unsuccessful_response
                        + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setAdapter(ArrayList<Children> children) {
        adapter = new RedditAdapter(getContext(), children);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void refreshData(ArrayList <Children> data) {
        adapter.clear();
        adapter.loadData(data);
        swipeContainer.setRefreshing(false);
    }
}