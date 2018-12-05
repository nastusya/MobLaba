package com.example.nastya.laba.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.MVPInterfaces.FavContract;
import com.example.nastya.laba.R;
import com.example.nastya.laba.adapters.RedditAdapter;
import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.presenter.FavPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteFragment extends Fragment implements FavContract.View {
    public final static String FAVOURITE = "Favourite";
    private RedditAdapter adapter;
    @BindView(R.id.favorite_recycler_view)
    protected RecyclerView recyclerView;
    private FavPresenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView(getFavourites());
        mPresenter = new FavPresenter( (ApplicationEx) getContext().getApplicationContext() );
        mPresenter.attachView(this);
        return view;
    }

    private ArrayList <Children> getFavourites() {
        ArrayList <Children> children = new ArrayList <>();
        SharedPreferences preferences;
        preferences = Objects.requireNonNull(getActivity()).getSharedPreferences(
                FAVOURITE, Context.MODE_PRIVATE);
        Map <String, ?> map = preferences.getAll();
        if (map != null) {
            for (Map.Entry <String, ?> entry : map.entrySet()) {
                final Children child = new Gson().
                        fromJson(entry.getValue().toString(), Children.class);
                children.add(child);
            }
        }
        return children;
    }

    private void initRecyclerView(ArrayList <Children> children) {
        adapter = new RedditAdapter(getContext(), children);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void displayFavourites(ArrayList <Children> childrenArrayList) {
        adapter.setItems(childrenArrayList);
        adapter.notifyDataSetChanged();
    }
}