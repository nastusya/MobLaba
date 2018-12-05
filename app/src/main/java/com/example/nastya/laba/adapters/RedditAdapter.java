package com.example.nastya.laba.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.R;
import com.example.nastya.laba.activities.MainActivity;
import com.example.nastya.laba.entity.children.Data;
import com.example.nastya.laba.fragments.ListDetailsFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import com.example.nastya.laba.entity.children.Children;

import java.util.ArrayList;

public class RedditAdapter extends RecyclerView.Adapter <RedditViewHolder> {

    private ArrayList <Children> children;
    private final Context context;

    public RedditAdapter(Context context, ArrayList <Children> children) {
        this.context = context;
        this.children = children;
    }

    public void loadData(ArrayList <Children> photos) {
        int position = getItemCount();
        this.children.addAll(photos);
        notifyItemRangeInserted(position, this.children.size());
    }

    @Override
    public final RedditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info,
                parent, false);
        return new RedditViewHolder(view);
    }

    @Override
    public final void onBindViewHolder(@NonNull RedditViewHolder holder, final int position) {
        final Data child = children.get(position).getData();
        Picasso.get().load(child.getThumbnail()).into(holder.image);
        holder.title.setText(child.getTitle());
        holder.user.setText(child.getSubreddit());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationEx mApplication = (ApplicationEx) context.getApplicationContext();
                mApplication.getFragmentHandler().goToDetails(children.get(position));
            }
        });
    }

    public void clear() {
        children.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return children.size();
    }
}