package com.example.co2mygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;

public class ResultScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        Intent intent = getIntent();

        Class[] productLine = (Class[]) getIntent().getSerializableExtra("productLine");

        init(productLine);

    }
    public void init(Class[] productLine){
        int columnCount = 2;
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        tableLayout.bringToFront();

        for(int i = 0; i < productLine.length; i++){
            TableRow tableRow = new TableRow(this);
                TextView productName = new TextView(this);
                TextView productCO2Value = new TextView(this);
                productName.setText(productLine[i].PRODUCT_NAME_DE[0]);
                String endResult = Double.toString(productLine[i].endResult[0]);
                productCO2Value.setText(endResult);
                tableRow.addView(productName);
                tableRow.addView(productCO2Value);
            tableLayout.addView(tableRow);
        }
    }

}