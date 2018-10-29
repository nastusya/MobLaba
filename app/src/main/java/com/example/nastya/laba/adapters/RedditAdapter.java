package com.example.nastya.laba.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nastya.laba.R;
import com.squareup.picasso.Picasso;

import com.example.nastya.laba.model.children.Children;

import java.util.ArrayList;

public class RedditAdapter extends RecyclerView.Adapter <RedditViewHolder> {
    private final ArrayList <Children> photos = new ArrayList <>();

    public RedditAdapter(ArrayList <Children> photos) {
        int position = getItemCount();
        this.photos.addAll(photos);
        notifyItemRangeInserted(position, this.photos.size());
    }

    @Override
    public final RedditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content,
                parent, false);
        return new RedditViewHolder(view);
    }

    @Override
    public final void onBindViewHolder(@NonNull RedditViewHolder holder, int position) {
        holder.title.setText(photos.get(position).getData().getTitle());
        holder.user.setText(photos.get(position).getData().getAuthorFullname());
        Picasso.get().load(photos.get(position).getData().getThumbnail()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}