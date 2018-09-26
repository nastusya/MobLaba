package com.example.nastya.laba;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientActivity extends AppCompatActivity {
    private ListView clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        clientList = findViewById(R.id.client_list);
        setClientData();
    }
    private void setClientData() {
        SharedPreferences sharedPref = getSharedPreferences(Const.DATA_KEY,
                Context.MODE_PRIVATE);
        String savedFirstName = sharedPref.getString(Const.FIRST_NAME_KEY, "");
        String savedLastName = sharedPref.getString(Const.LAST_NAME_KEY, "");
        String savedPhone = sharedPref.getString(Const.PHONE_NUMBER_KEY, "");
        String[] savedClientData = new String[]{savedFirstName, savedLastName, savedPhone};
        List<String> listSavedClient = new ArrayList<>(Arrays.asList(savedClientData));
        ArrayAdapter<String> adapterList = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listSavedClient);
        clientList.setAdapter(adapterList);
    }
}
