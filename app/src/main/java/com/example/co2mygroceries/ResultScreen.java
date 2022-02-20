package com.example.co2mygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.co2mygroceries.data.DataBaseHelper;
import com.google.android.gms.vision.Frame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        Intent intent = getIntent();

        Class[] productLine = (Class[]) getIntent().getSerializableExtra("productLine");

        init(productLine);

    }
    public void init(Class[] productLine){


        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        TableRow tableRowProductName = new TableRow(this);
        tableLayout.bringToFront();



        for(int i = 0; i < productLine.length; i++){
            TableRow tableRow = new TableRow(this);
            tableRowProductName.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                TextView productName = new TextView(this);
                TextView prodcutQuantity = new TextView(this);
                TextView productCO2Value = new TextView(this);
                Spinner productSpinner = new Spinner(this);

                //productName.setText(productLine[i].PRODUCT_NAME_DE[0]);
                String endResult = Double.toString(productLine[i].endResult[0]);
                productCO2Value.setText(endResult + "kg");
                String quantity = Double.toString(productLine[i].PRODUCT_QUANTITY[0]);
                prodcutQuantity.setText(quantity + "g");
                Spinner spinner = new Spinner(this);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,productLine[i].all_Products);
                spinner.setAdapter(arrayAdapter);
                spinner.setOnItemSelectedListener(this);
                tableRow.addView(spinner);
                tableRow.addView(prodcutQuantity);
                tableRow.addView(productCO2Value);
            tableLayout.addView(tableRow);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedProduct = parent.getItemAtPosition(position).toString();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        try {
            dataBaseHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataBaseHelper.openDataBase();

        dataBaseHelper.getCO2Value(dataBaseHelper.getProdcut_IDForSpinner(selectedProduct));
        dataBaseHelper.getQuantity(dataBaseHelper.getProdcut_IDForSpinner(selectedProduct));



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}