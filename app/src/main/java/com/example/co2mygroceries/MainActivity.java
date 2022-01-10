package com.example.co2mygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.co2mygroceries.data.DataBaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
TextView scanbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.text);



        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        try {
            dataBaseHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBaseHelper.openDataBase();
        dataBaseHelper.getProductName();
        String Test1 = "Ajvar";
        String Test2 = "200g";
        /*dataBaseHelper.writeProductInfo(Test1, Test2);
        dataBaseHelper.close();*/




        scanbtn = (TextView) findViewById(R.id.scanbutton);
        scanbtn.setOnClickListener (new View.OnClickListener() {
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