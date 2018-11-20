package com.example.nastya.laba.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nastya.laba.R;
import com.example.nastya.laba.adapters.PageAdapter;

public class TabFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab_fragment, container, false);
        final TabLayout tabLayout = view.findViewById(R.id.navigation);
        setUpTabs(tabLayout);
        final ViewPager viewPager = view.findViewById(R.id.screen);
        setUpViewPager(viewPager, tabLayout);
        return view;
    }

    private void setUpTabs(final TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_list)
                .setText(R.string.title_list));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite)
                .setText(R.string.title_favourite));
    }

    private void setUpViewPager(final ViewPager viewPager, final TabLayout tabLayout) {
        final PageAdapter pagerAdapter = new PageAdapter(getChildFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(final TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(final TabLayout.Tab tab) {
            }
        });
    }
}