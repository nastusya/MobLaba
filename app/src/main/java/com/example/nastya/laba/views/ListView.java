package com.example.nastya.laba.views;

import com.example.nastya.laba.entity.children.Children;

import java.util.ArrayList;

public interface ListView {
    void setDataToRecyclerView(ArrayList <Children> childrenArrayList);

    void refreshData(ArrayList <Children> children);

    void onResponseFailure(Throwable throwable);
}
