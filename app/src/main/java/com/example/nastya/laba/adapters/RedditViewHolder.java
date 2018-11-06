package com.example.nastya.laba.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nastya.laba.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class RedditViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tags)
    protected TextView title;
    @BindView(R.id.user)
    protected TextView user;
    @BindView(R.id.image)
    protected ImageView image;

    RedditViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}