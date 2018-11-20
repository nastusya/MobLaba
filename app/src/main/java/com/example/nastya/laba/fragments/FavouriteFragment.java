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
import com.example.nastya.laba.activities.MainActivity;
import com.example.nastya.laba.adapters.RedditAdapter;
import com.example.nastya.laba.http_client.OnChildrenClickListener;
import com.example.nastya.laba.model.children.Children;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteFragment extends Fragment {
    RedditAdapter adapter;
    private ArrayList <Children> children = new ArrayList <>();
    public final static String FAVOURITE = "Favourite";
    public final static String ARG_TITLE = "Children";
    @BindView(R.id.favorite_recycler_view)
    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        ButterKnife.bind(this, view);
        children = null;
        if (getActivity() != null) {
            initRecyclerView();
            getPreferences();
        }
        return view;
    }

    private void getPreferences() {
        SharedPreferences preferences;
        preferences = Objects.requireNonNull(getActivity()).getSharedPreferences(
                FAVOURITE, Context.MODE_PRIVATE);
        Map <String, ?> map = preferences.getAll();
        if (map != null) {
            for (Map.Entry <String, ?> entry : map.entrySet()) {
                final Children children;
                children = new Gson().
                        fromJson(entry.getValue().toString(), Children.class);
                children.getData();
            }
        }
    }

    private void initRecyclerView() {
        children = new ArrayList <>();
        adapter = new RedditAdapter();
        adapter.setOnCharacterClickListener(new OnChildrenClickListener() {
            @Override
            public void onChildrenClick(Children children) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ARG_TITLE, (Serializable) children);

                    ListDetailsFragment listDetailsFragment = new ListDetailsFragment();
                    listDetailsFragment.setArguments(bundle);

                    mainActivity.setFragment(listDetailsFragment);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}