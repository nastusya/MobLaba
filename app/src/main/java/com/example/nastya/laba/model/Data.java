package com.example.nastya.laba.model;

import com.example.nastya.laba.model.children.Children;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

class Data {

    @SerializedName("modhash")
    @Expose
    private String modhash;

    @SerializedName("children")
    @Expose
    private ArrayList<Children> children;

    public String getModhash() {
        return modhash;
    }

    public ArrayList<Children> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Data{" +
                "modhash='" + modhash + '\'' +
                ", children=" + children +
                '}';
    }
}