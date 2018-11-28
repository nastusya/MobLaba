package com.example.nastya.laba.MVPInterfaces;

import com.example.nastya.laba.entity.children.Children;

public interface DetailsContract {
    interface Model {
        void setFavourite(Children children);

        boolean checkFavourite(Children children);
    }

    interface View {
        void displayChildren(final Children children, final boolean isFav);
    }

    interface Presenter {
        void makeFavourite();

        Children getCurrentChildren();
    }

}