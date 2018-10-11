package com.example.nastya.laba.model.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Children {

    @SerializedName("data")
    @Expose
    private Data data;

    @SerializedName("kind")
    @Expose
    private String kind;

    public Data getData() {
        return data;
    }

    public String getKind() {
        return kind;
    }

    @Override
    public String toString() {
        return "Children{" +
                "data=" + data +
                ", kind='" + kind + '\'' +
                '}';
    }
}
