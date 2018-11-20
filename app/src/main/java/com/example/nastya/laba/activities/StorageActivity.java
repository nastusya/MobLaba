package com.example.nastya.laba.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nastya.laba.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;

public class StorageActivity extends AppCompatActivity {
    @BindView(R.id.storageListView)
    ListView storageListView;
    public final static String PREFERENCE = "Preference";
    public final static String RECORD = "Record";
    protected SharedPreferences sharedPreferences;
    Set <String> stringSet = new HashSet <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        sharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        stringSet = sharedPreferences.getStringSet(RECORD, null);
        ArrayList <String> childrenLost = new ArrayList <>(stringSet);
        ArrayAdapter <String> arrayAdapter = new ArrayAdapter <>(this,
                android.R.layout.simple_list_item_1, childrenLost);
        storageListView.setAdapter(arrayAdapter);
    }
}