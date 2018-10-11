package com.example.nastya.laba.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feed {

    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public String getKind() {
        return kind;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "kind='" + kind + '\'' +
                ", data=" + data +
                '}';
    }
}