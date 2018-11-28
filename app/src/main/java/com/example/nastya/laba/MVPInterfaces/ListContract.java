package com.example.nastya.laba.MVPInterfaces;

import com.example.nastya.laba.entity.children.Children;

import java.util.ArrayList;

public interface ListContract {
    interface Presenter {
        void loadData();

        void seletedChild(Children children);
    }

    interface View {
        void noData();

        void displayChildren(final ArrayList <Children> childrenArrayList);
    }

    interface Model {
        ArrayList <Children> getChildList();
    }
}
