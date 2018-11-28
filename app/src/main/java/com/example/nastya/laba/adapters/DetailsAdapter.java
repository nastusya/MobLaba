package com.example.nastya.laba.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nastya.laba.R;
import com.example.nastya.laba.entity.children.Children;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter <RedditViewHolder> {
    private ArrayList <Children> photos = new ArrayList <>();

    public void setItems(ArrayList <Children> children) {
        this.photos = children;
    }

    @NonNull
    @Override
    public RedditViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewTypei) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_reddit_list,
                viewGroup, false);
        return new RedditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RedditViewHolder holder, int position) {
        Picasso.get().load(photos.get(position).getData().getThumbnail()).into(holder.image);
        holder.title.setText(photos.get(position).getData().getTitle());
        holder.user.setText(photos.get(position).getData().getSubreddit());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}