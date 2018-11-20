package com.example.nastya.laba.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nastya.laba.fragments.FavouriteFragment;
import com.example.nastya.laba.fragments.ListFragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    private static final int LIST = 0;
    private static final int FAVOURITE = 1;
    private final int mNumOfTabs;
    private final ListFragment listFragment;
    private final FavouriteFragment favouriteFragment;

    public PageAdapter(final FragmentManager fragmentManager, final int numOfTabs) {
        super(fragmentManager);
        this.mNumOfTabs = numOfTabs;
        listFragment = new ListFragment();
        favouriteFragment = new FavouriteFragment();
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case LIST:
                return listFragment;
            case FAVOURITE:
                return favouriteFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}