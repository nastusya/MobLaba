package com.example.nastya.laba.model.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("author_fullname")
    @Expose
    private String authorFullname;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("link_flair_text_color")
    @Expose
    private String linkFlairTextColor;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("subreddit")
    @Expose
    private String subreddit;

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public String getAuthorFullname() {
        return authorFullname;
    }

    public String getTitle() {
        return title;
    }

    public String getLinkFlairTextColor() {
        return linkFlairTextColor;
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
                ", link_flair_text_color='" + linkFlairTextColor + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", subreddit='" + subreddit + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}