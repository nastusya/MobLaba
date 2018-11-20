package com.example.nastya.laba.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nastya.laba.R;
import com.example.nastya.laba.http_client.OnChildrenClickListener;
import com.squareup.picasso.Picasso;

import com.example.nastya.laba.model.children.Children;

import java.util.ArrayList;

public class RedditAdapter extends RecyclerView.Adapter <RedditViewHolder> {
    private ArrayList <Children> photos = new ArrayList <>();
    private static RedditAdapter redditAdapter = null;
    private OnChildrenClickListener listener;
    private Context mContext;

    public static RedditAdapter getRedditAdapter() {
        if (redditAdapter == null) {
            redditAdapter = new RedditAdapter();
        }
        return redditAdapter;
    }

    public RedditAdapter() {
    }

    @Override
    public final RedditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_reddit_list,
                parent, false);
        mContext = parent.getContext();
        return new RedditViewHolder(view);
    }

    @Override
    public final void onBindViewHolder(@NonNull RedditViewHolder holder, int position) {
        final Children children = photos.get(position);
        Picasso.get().load(photos.get(position).getData().getThumbnail()).into(holder.image);
        holder.title.setText(photos.get(position).getData().getTitle());
        holder.user.setText(photos.get(position).getData().getSubreddit());
        if (children.getData().getTitle().equals(mContext.getString(R.string.app_name))) {
            holder.title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
            holder.user.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        } else {
            holder.user.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onChildrenClick(children);
            }
        });
    }

    public void setOnCharacterClickListener(OnChildrenClickListener listener) {
        this.listener = listener;
    }

    public void setItems(ArrayList <Children> photos) {
        this.photos = photos;
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}