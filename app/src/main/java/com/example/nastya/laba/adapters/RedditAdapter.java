package com.example.nastya.laba.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nastya.laba.R;
import com.example.nastya.laba.activities.MainActivity;
import com.example.nastya.laba.fragments.ListDetailsFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import com.example.nastya.laba.model.children.Children;

import java.util.ArrayList;

public class RedditAdapter extends RecyclerView.Adapter <RedditViewHolder> {

    private ArrayList <Children> children;
    private static final String DETAILS = "details";
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
        Picasso.get().load(children.get(position).getData().getThumbnail()).into(holder.image);
        holder.title.setText(children.get(position).getData().getTitle());
        holder.user.setText(children.get(position).getData().getSubreddit());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListDetailsFragment fragment = new ListDetailsFragment();
                Bundle bundle = new Bundle();
                Log.e("BLABLABLABLA", "BLABLDKSLD:::'");
                bundle.putString(DETAILS, new Gson().toJson(children.get(position)));
                fragment.setArguments(bundle);
                ((MainActivity) view.getContext()).setFragment(fragment);
            }
        });
    }

    public void clear() {
        children.clear();
        notifyDataSetChanged();
    }

    public void setItems(ArrayList <Children> photos) {
        this.children = photos;
    }

    @Override
    public int getItemCount() {
        return children.size();
    }
}