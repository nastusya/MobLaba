package com.example.nastya.laba.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nastya.laba.ApplicationEx;
import com.example.nastya.laba.FragmentHandler;
import com.example.nastya.laba.R;
import com.example.nastya.laba.fragments.ListFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); ApplicationEx mApplication = (ApplicationEx) getApplicationContext();
        FragmentHandler fragmentHandler = new FragmentHandler(this);
        mApplication.setFragmentHandler(fragmentHandler);
        mApplication.getFragmentHandler().setFragment(new ListFragment());
    }
}