package com.example.nastya.laba.model.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Data {

    @SerializedName("author_fullname")
    @Expose
    private String author_fullname;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("link_flair_text_color")
    @Expose
    private String link_flair_text_color;

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

    public String getAuthor_fullname() {
        return author_fullname;
    }

    public String getTitle() {
        return title;
    }

    public String getLink_flair_text_color() {
        return link_flair_text_color;
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
                "author_fullname='" + author_fullname + '\'' +
                ", title='" + title + '\'' +
                ", link_flair_text_color='" + link_flair_text_color + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", subreddit='" + subreddit + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}