package com.example.nastya.laba.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nastya.laba.R;
import com.example.nastya.laba.controller.Controller;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller controller = new Controller();
        controller.start();
    }
}