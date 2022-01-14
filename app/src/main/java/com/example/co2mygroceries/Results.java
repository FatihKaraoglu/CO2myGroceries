package com.example.co2mygroceries;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.co2mygroceries.data.DataBaseHelper;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.Levenshtein;
import info.debatty.java.stringsimilarity.RatcliffObershelp;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;


public class Results extends AppCompatActivity {
    String result;
    String[] splittedResult;
    String[][] splittedStringWhitespace;
    Class[] productLine;
    static int ANZAHL_PRODUKTE = 414;
    private JaroWinkler jaroWinkler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        result = bundle.getString("result");

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        try {
            dataBaseHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBaseHelper.openDataBase();
        String[] product_Names = dataBaseHelper.getName();

        splittedResult = splitString(result);
        productLine = createClassObjects(splittedResult.length);
        // set ReceiptLine in ProductObject
        for(int i = 0; i < splittedResult.length; i++){
            productLine[i].receiptLine = splittedResult[i];
        }
        jaroWinkler(product_Names);

        for (int i = 0; i < productLine.length; i++){
            for (int y = 0; y < 3; y++){
                if (productLine[i].fuzzyScore[y] > 0.1) {
                    productLine[i].PRODUCT_NAME[y] = product_Names[productLine[i].PRODUCT_INDEX[y]];
                    productLine[i].PRODUCT_ID[y] = dataBaseHelper.getProduct_ID(productLine[i].PRODUCT_NAME[y]);
                    productLine[i].Total_kg_CO2_eq_kg[y] = dataBaseHelper.getCO2Value(productLine[i].PRODUCT_ID[y]);

                    Log.i("Never Mind", productLine[i].receiptLine + " : " + productLine[i].PRODUCT_NAME[y] + " CO2 per KG equals: " + productLine[i].Total_kg_CO2_eq_kg[y] + " fuzzyScore: " + Arrays.toString(productLine[i].fuzzyScore));
                }
            }
        }


    }

    public String[] splitString(String stringToSplit) {
        String[] splittedStringNewLine;
        stringToSplit = stringToSplit.replaceAll("\\d", "");
        stringToSplit = stringToSplit.replaceAll("[^\\w\\s]", "");
        stringToSplit = stringToSplit.replaceAll("(?s)(?<!\\S).(?!\\S)", "");
        stringToSplit = stringToSplit.replaceAll("(?m)^\\s*$[\n\r]{1,}", "");
        splittedStringNewLine = stringToSplit.split("\\r?\\n");
        return splittedStringNewLine;
    }

    public String[][] splitStringWhitespace(String[] splittedStringNewLine) {
        String[][] array;
        array = new String[splittedResult.length][];
        for (int i = 0; i < splittedStringNewLine.length; i++) {
            String[] wordStringSplit = splittedStringNewLine[i].trim().split("\\s+");
            array[i] = new String[wordStringSplit.length];
            array[i] = wordStringSplit;
        }
        return array;
    }
    public Class[] createClassObjects(int splittedResultLength){
        Class[] ProductLine = new Class[splittedResultLength];
        for (int i = 0; i < splittedResultLength; i++){
            ProductLine[i] = new Class();
        }
        return ProductLine;
    }

    public void jaroWinkler(String[] productName){
        JaroWinkler jaroWinkler = new JaroWinkler();
        double[] scoreArray = new double[productName.length];
        for (int i = 0; i < productLine.length; i++) {
            for(int x = 0; x < productName.length; x++){
                scoreArray[x] = jaroWinkler.similarity(productLine[i].receiptLine,productName[x]);
            }
            biggestElements(scoreArray, i);

        }
    }
    public void biggestElements(double[] scoreArray, int indexProductLine){
        double first = 0;
        double second = 0;
        double third = 0;
        int firstIndex = 0;
        int secondIndex = 0;
        int thirdIndex = 0;

        for (int i = 0; i <scoreArray.length ; i++) {
            double current = scoreArray[i];
            if(first<current){
                third = second;
                second = first;
                first = current;
                firstIndex = i;
            }else if(second<current){
                third = second;
                second = current;
                secondIndex = i;
            }else if(third<current){
                third=current;
                thirdIndex = i;
            }

        }
        double[] fuzzyScore = new double[] {first, second , third};
        int [] index = new int []{firstIndex, secondIndex, thirdIndex};
        productLine[indexProductLine].fuzzyScore = fuzzyScore;
        productLine[indexProductLine].PRODUCT_INDEX = index;

    }


}




