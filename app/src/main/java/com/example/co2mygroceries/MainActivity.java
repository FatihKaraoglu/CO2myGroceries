package com.example.co2mygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.co2mygroceries.data.ProductDbHelper;
import com.example.co2mygroceries.data.FoodContracts;

public class MainActivity extends AppCompatActivity {
TextView scanbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProductDbHelper mDbHelper = new ProductDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        scanbtn = (TextView) findViewById(R.id.scanbutton);
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openscanActivity();
            }
        });
    }

    public void openscanActivity(){
        Intent intent = new Intent(this, Scan.class);
        startActivity(intent);
    }

}