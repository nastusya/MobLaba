package com.example.nastya.laba.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.R;
import com.example.nastya.laba.TabNavigation;
import com.example.nastya.laba.fragments.FavouriteFragment;
import com.example.nastya.laba.fragments.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.navigation_menu)
    BottomNavigationView bottomNavigation;
    TabNavigation tabNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationListener);
        tabNavigation = new TabNavigation(getSupportFragmentManager());
        ((ApplicationEx) getApplication()).setTabNavigation(tabNavigation);
        tabNavigation.setFragment(new ListFragment(), false);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.home_button:
                            selectedFragment = new ListFragment();
                            break;
                        case R.id.favorites_button:
                            selectedFragment = new FavouriteFragment();
                            break;
                    }
                    tabNavigation.setFragment(selectedFragment, false);
                    return true;
                }
            };
}