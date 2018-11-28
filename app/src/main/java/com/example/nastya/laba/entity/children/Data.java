package com.example.nastya.laba.entity.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("author_fullname")
    @Expose
    private String authorFullname;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("subreddit")
    @Expose
    private String subreddit;

    public String getAuthorFullname() {
        return authorFullname;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSubreddit() {
        return subreddit;
    }


    @Override
    public String toString() {
        return "Data{" +
                "author_fullname='" + authorFullname + '\'' +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", subreddit='" + subreddit + '\'' +
                '}';
    }
}