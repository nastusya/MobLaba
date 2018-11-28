package com.example.nastya.laba;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.nastya.laba.fragments.ListDetailsFragment;

public class TabNavigation {
    FragmentManager fragmentManager;

    public TabNavigation(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }


    public void setFragment(final Fragment fragment, final boolean addToBackStack) {
        final FragmentTransaction transaction = fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void showDetailsFragment() {
        setFragment(new ListDetailsFragment(), true);
    }
}