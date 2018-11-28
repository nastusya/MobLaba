package com.example.nastya.laba.MVPInterfaces;

import com.example.nastya.laba.entity.children.Children;

import java.util.ArrayList;

public interface FavContract {

    interface Presenter {
        void loadData();

        void seletedChild(Children children);
    }

    interface View {
        void displayFavourites(final ArrayList <Children> childrenArrayList);
    }

    interface Model {
        ArrayList <Children> getFavourite();
    }
}
