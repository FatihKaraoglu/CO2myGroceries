package com.example.co2mygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.co2mygroceries.data.ProductDbHelper;
import com.example.co2mygroceries.data.FoodContracts;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProductDbHelper mDbhelper = new ProductDbHelper(this);
        SQLiteDatabase db = mDbhelper.getReadableDatabase();
    }
}