package com.example.co2mygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.co2mygroceries.data.DataBaseHelper;

import java.io.IOException;

public class SearchProducts extends AppCompatActivity {

    String[] productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        try {
            dataBaseHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBaseHelper.openDataBase();

        productName = dataBaseHelper.getName();
        ResultScreen resultScreen = new ResultScreen();
    }


}