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
        API api = new API();

        api.buildTable(getApplicationContext());

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        try {
            dataBaseHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBaseHelper.openDataBase();
        String [] productName;
        productName = dataBaseHelper.getProductName();
        int []index = fuzzySearch(productName, splittedResult);

        for (int i = 0; i < splittedResult.length; i++) {
            //Log.i("MSG1", splittedResult[i]);
            //Log.i("MSG2", productName[index[i]]);
        }

    }

    public String[] splitString(String stringToSplit){
        String[] splittedString;
        splittedString = stringToSplit.split("\\s+");
        //splittedString = stringToSplit.split("\\r?\\n");
        return splittedString;
    }

    public int[]fuzzySearch(String [] productName , String []splittedResult ) {
        int[][] fuzzyScore = new int[ANZAHL_PRODUKTE][splittedResult.length];

        for (int y = 0; y < splittedResult.length; y++) {
            for (int i = 0; i < ANZAHL_PRODUKTE; i++) {
                fuzzyScore[i][y] = FuzzySearch.ratio(productName[i], splittedResult[y]);
            }
        }
        int[] max = new int[splittedResult.length];
        max[0] = fuzzyScore[0][0];
        int[] maxIndex = new int[splittedResult.length];
        for (int y = 0; y < splittedResult.length; y++) {
            for (int i = 0; i < ANZAHL_PRODUKTE; i++) {
                if (fuzzyScore[i][y] >= max[y]) {
                    max[y] = fuzzyScore[i][y];
                    maxIndex[y] =  i;

                }
            }
        }
        for (int y = 0; y < splittedResult.length; y++) {
            Log.i("Score", splittedResult[y]+" "+productName[maxIndex[y]] +" "+ String.valueOf(max[y]));
        }
        return maxIndex;
    }
}


