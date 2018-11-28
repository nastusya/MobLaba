package com.example.nastya.laba.entity;

import com.example.nastya.laba.entity.children.Children;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    @SerializedName("modhash")
    @Expose
    private String modhash;

    @SerializedName("children")
    @Expose
    private ArrayList <Children> children;

    public ArrayList <Children> getChildren() {
        return children;
    }

    public String getModhash() {
        return modhash;
    }

    @Override
    public String toString() {
        return "Data{" +
                "modhash='" + modhash + '\'' +
                ", children=" + children +
                '}';
    }
}