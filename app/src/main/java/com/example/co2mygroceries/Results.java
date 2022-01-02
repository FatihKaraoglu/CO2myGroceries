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
        productName = dataBaseHelper.getProductName();
        int i = fuzzySearch(productName, splittedResult);
        Log.i("MSG", productName[i] );


    }
    public String[] splitString(String stringToSplit){
        String[] splittedString;
        splittedString = stringToSplit.split("\\r?\\n");
        return splittedString;
    }

    public int fuzzySearch(String [] productName , String []splittedResult ){
        int []fuzzyScore = new int[413];
        for(int i = 0 ; i < 413; i++){
            fuzzyScore[i] = FuzzySearch.ratio(productName[i], splittedResult[0]);
        }
        int max = fuzzyScore[0];
        int highestIndex = 0;
        for (int i = 0; i < fuzzyScore.length; i++){
            if(fuzzyScore[i] > max){
                highestIndex = i;
                max = fuzzyScore[i];
            }

        }
        return highestIndex;

    }
}
