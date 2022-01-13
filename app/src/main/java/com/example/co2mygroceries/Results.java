package com.example.co2mygroceries;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.co2mygroceries.data.DataBaseHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class Results extends AppCompatActivity {
    String result;
    String [] splittedResult;
    String [][]splittedStringWhitespace;
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
        splittedStringWhitespace = splitWhitespace(splittedResult);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        try {
            dataBaseHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBaseHelper.openDataBase();
        String [] productName;
    }

    public String[] splitString(String stringToSplit){
        String[] splittedStringNewLine;
        stringToSplit = stringToSplit.replaceAll("\\d","");
        stringToSplit = stringToSplit.replaceAll("[^\\w\\s]", "");
        stringToSplit = stringToSplit.replaceAll("(?s)(?<!\\S).(?!\\S)", "");
        stringToSplit = stringToSplit.replaceAll("(?m)^\\s*$[\n\r]{1,}", "");
        splittedStringNewLine = stringToSplit.split("\\r?\\n");
        return splittedStringNewLine;
    }

    public String[][] splitWhitespace(String[] splittedStringNewLine){
        String[][] splittedStringWhitespace = new String[splittedStringNewLine.length][10];
        String[] whitespace = new String[10];
        for (int i = 0; i < splittedStringNewLine.length; i++) {
            whitespace = splittedStringNewLine[i].split("\\s+");
            for (int y = 0; y < whitespace.length; y++) {
                splittedStringWhitespace[i][y] = whitespace[y];
                Log.i("MSG", splittedStringWhitespace[i][y]);
            }
        }
        return splittedStringWhitespace;
    }
}
    public void buildQuery(String [][]splittedStringWhitespace){
        String [][] recognizedProdcuts = splittedStringWhitespace;
        String query = "SELECT PRODUCT_NAME FROM INFO WHERE PRODUCT_NAME LIKE";
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append()

    }





