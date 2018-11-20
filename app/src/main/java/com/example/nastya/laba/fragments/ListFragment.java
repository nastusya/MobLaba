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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.R;
import com.example.nastya.laba.activities.MainActivity;
import com.example.nastya.laba.adapters.RedditAdapter;
import com.example.nastya.laba.http_client.OnChildrenClickListener;
import com.example.nastya.laba.model.Feed;
import com.example.nastya.laba.model.children.Children;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private ArrayList <Children> children = new ArrayList <>();
    @BindView(R.id.list_photos)
    protected RecyclerView recyclerView;
    public final static String ARG_TITLE = "Children";
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
            detailsList();
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
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
                     children = response.body().getData().getChildren();
                    displayDetails();
                }
            }

            @Override
            public void onFailure(Call <Feed> call, Throwable throwable) {
                Toast.makeText(getActivity(), R.string.unsuccessful_response
                        + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void detailsList() {
        adapter = RedditAdapter.getRedditAdapter();
        adapter.setOnCharacterClickListener(new OnChildrenClickListener() {
            @Override
            public void onChildrenClick(Children children) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ARG_TITLE, (Serializable) children);
                    ListDetailsFragment listItemFragment = new ListDetailsFragment();
                    listItemFragment.setArguments(bundle);
                    mainActivity.setFragment(listItemFragment);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void displayDetails() {
        adapter.setItems(children);
        adapter.notifyDataSetChanged();
    }
}