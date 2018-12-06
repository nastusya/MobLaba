package com.example.nastya.laba.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.nastya.laba.R;
import com.example.nastya.laba.adapters.RedditAdapter;
import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.presenter.ListPresenter;
import com.example.nastya.laba.presenter.ListPrisenterImpl;
import com.example.nastya.laba.views.ListView;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements ListView {
    private ListPresenter presenter;
    @BindView(R.id.list_photos)
    protected RecyclerView recyclerView;
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeContainer;
    private RedditAdapter adapter;
    @BindView(R.id.move)
    Button moveToFav;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reddit_list, container, false);
        if (getActivity() != null) {
            ButterKnife.bind(this, view);
            initializeRecyclerView();
            presenter = new ListPrisenterImpl(this);
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    presenter.updateDataFromServer(getActivity());
                }
            });
        }

        moveToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.goToFavourites(getActivity());    }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.requestDataFromServer(getActivity());
    }

    private void initializeRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void setDataToRecyclerView(ArrayList <Children> childrenArrayList) {
        adapter = new RedditAdapter(getActivity(), childrenArrayList);
        recyclerView.setAdapter(adapter);
    }

    public void refreshData(ArrayList <Children> data) {
        adapter.clear();
        adapter.loadData(data);
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), R.string.unsuccessful_response
                + throwable.getMessage(), Toast.LENGTH_SHORT).show();

    }
}