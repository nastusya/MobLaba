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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.MVPInterfaces.ListContract;
import com.example.nastya.laba.R;
import com.example.nastya.laba.activities.MainActivity;
import com.example.nastya.laba.adapters.RedditAdapter;
import com.example.nastya.laba.entity.Feed;
import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.presenter.ListPresenter;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment implements ListContract.View {
    private boolean isChange = false;
    @BindView(R.id.list_photos)
    protected RecyclerView recyclerView;
    public ImageView noData;
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeContainer;
    @BindView(R.id.move)
    Button moveToFav;
    private RedditAdapter adapter;
    private ListPresenter mPresenter;
    private ArrayList <Children> children;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reddit_list, container, false);
        mPresenter = new ListPresenter( (ApplicationEx) getContext().getApplicationContext() );
        mPresenter.attachView(this);
        if (getActivity() != null) {
            ButterKnife.bind(this, view);
            setAdapter(children);
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    isChange = true;
                    mPresenter.loadData();
                    swipeContainer.setRefreshing(false);
                }
            });
        }

        moveToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) view.getContext())
                        .setFragment(new FavouriteFragment(), true);
            }
        });

        mPresenter.loadData();
        return view;
    }

    private void setAdapter(ArrayList <Children> children) {
        adapter = new RedditAdapter(getContext(), children);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void noData() {
        adapter.setItems(null);
        adapter.notifyDataSetChanged();
        noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayChildren(ArrayList <Children> childrenArrayList) {
        adapter.setItems(childrenArrayList);
        adapter.notifyDataSetChanged();
        noData.setVisibility(View.INVISIBLE);
    }
}