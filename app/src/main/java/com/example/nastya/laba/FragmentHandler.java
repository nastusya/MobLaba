package com.example.nastya.laba;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.nastya.laba.activities.MainActivity;
import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.fragments.ListDetailsFragment;
import com.google.gson.Gson;

import static com.example.nastya.laba.ApplicationEx.DETAILS;

public class FragmentHandler {

    private final MainActivity activity;

    private Fragment currentFragment;

    public FragmentHandler(final MainActivity activity) {
        this.activity = activity;
    }

    public void setFragment(final Fragment fragment) {
        activity.getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
        currentFragment = fragment;
    }

    public void goToDetails(final Children children) {
        final ListDetailsFragment fragment = new ListDetailsFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(DETAILS, new Gson().toJson(children));
        fragment.setArguments(bundle);
        setFragment(fragment);
    }

    public Children getArguments() {
        if (currentFragment instanceof ListDetailsFragment) {
            final Bundle arguments = currentFragment.getArguments();
            if (arguments != null) {
                Children children = new Gson()
                        .fromJson(arguments.getString(DETAILS), Children.class);
                return children;
            }
        }
        return null;
    }
}