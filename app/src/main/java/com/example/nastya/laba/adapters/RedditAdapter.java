package com.example.nastya.laba.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nastya.laba.R;
import com.squareup.picasso.Picasso;

import com.example.nastya.laba.model.children.Children;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RedditAdapter extends RecyclerView.Adapter <RedditAdapter.RedditViewHolder> {
    private final ArrayList <Children> photos;

    public RedditAdapter(ArrayList <Children> photos) {
        this.photos = photos;
    }

    @Override
    public final RedditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content,
                parent, false);
        return new RedditViewHolder(v);
    }

    @Override
    public final void onBindViewHolder(@NonNull RedditViewHolder holder, int position) {
        holder.title.setText(photos.get(position).getData().getTitle());
        holder.author.setText(photos.get(position).getData().getAuthorFullname());
        Picasso.get().load(photos.get(position).getData().getThumbnail()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    @Override
    public final void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public final void clear() {
        photos.clear();
        notifyDataSetChanged();
    }

    final class RedditViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tags)
        TextView title;
        @BindView(R.id.user)
        TextView author;
        @BindView(R.id.image)
        ImageView image;

        RedditViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
