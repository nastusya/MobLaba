package com.example.nastya.laba.views;

import com.example.nastya.laba.entity.children.Children;

public interface DetailsView {
    void addToFavourite();

    void removeFromFavourite();

    void markFavourite(boolean favourite);

    void setItems(Children hit);
}
