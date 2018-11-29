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

import com.example.nastya.laba.R;
import com.example.nastya.laba.adapters.RedditAdapter;
import com.example.nastya.laba.model.children.Children;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteFragment extends Fragment {

    public final static String FAVOURITE = "Favourite";
    RedditAdapter adapter;
    private ArrayList <Children> children = new ArrayList <>();
    @BindView(R.id.favorite_recycler_view)
    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView(getFavourites());
        return view;
    }

    private ArrayList <Children> getFavourites() {
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
}