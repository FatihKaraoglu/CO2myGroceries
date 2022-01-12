package com.example.co2mygroceries;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.co2mygroceries.data.DataBaseHelper;

import java.io.IOException;
import java.util.Arrays;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class Results extends AppCompatActivity {
    String result;
    String [] splittedResult;
    static int ANZAHL_PRODUKTE = 414;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        result = bundle.getString("result");

        splittedResult = splitString(result);
        Log.i("MSG", result);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        try {
            dataBaseHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBaseHelper.openDataBase();
        String [] productName;
        int []id = fuzzySearch(splittedResult, dataBaseHelper);
        for (int i = 0; i < splittedResult.length; i++){
            Log.i("MSG", String.valueOf(id[i]));
        }

    }

    public String[] splitString(String stringToSplit){
        String[] splittedString;
        //splittedString = stringToSplit.split("\\s+");
        splittedString = stringToSplit.split("\\r?\\n");
        return splittedString;
    }

    public int[]fuzzySearch(String []splittedResult, DataBaseHelper dataBaseHelper ) {
        String productName;
        int oldScore = 0;
        int newScore;
        int count = dataBaseHelper.getDatabaseCount();
        int ID[] = new int[splittedResult.length];
        for (int y = 0; y < splittedResult.length; y++) {
            for (int i = 0; i < dataBaseHelper.getDatabaseCount(); i++) {
                productName = dataBaseHelper.getProdcutName(i);
                Log.i("MSG", productName);
                if (FuzzySearch.ratio(productName, splittedResult[y]) > 80 && FuzzySearch.ratio(productName, splittedResult[y]) > oldScore) {
                    Log.i("MSG", String.valueOf(FuzzySearch.ratio(productName, splittedResult[y] )));
                    oldScore = FuzzySearch.ratio(productName, splittedResult[y]);
                    ID[y] = dataBaseHelper.getProduct_ID(productName);
                }
            }
        }
        return ID;
    }
}




