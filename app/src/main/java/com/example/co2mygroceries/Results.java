package com.example.co2mygroceries;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Results extends AppCompatActivity {
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        result = bundle.getString("result");
        Log.i("MSG", result);

        if(result.contains("Skyr")){
            Log.i("Count", "1*Fladenbrot");
        }
    }
}
