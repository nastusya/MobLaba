package com.example.nastya.laba;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Gson gson = new Gson();
        List<UserModel> listOfUsers;
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                "user_list", Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString("user_list", "");
        Log.i("users", jsonPreferences);

        if(!jsonPreferences.equals("")){
            Type type = new TypeToken<List<UserModel>>() {}.getType();
            listOfUsers = gson.fromJson(jsonPreferences, type);
            Log.i("users", listOfUsers.toString());

            ListView userListView = findViewById(R.id.client_list);
            ArrayAdapter<UserModel> arrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    listOfUsers);

            userListView.setAdapter(arrayAdapter);
        }
    }
}
