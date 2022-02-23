package com.example.co2mygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.co2mygroceries.data.DataBaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewRecyclerView extends AppCompatActivity {
    ArrayList<Class> itemShowClassArrayList;
    RecyclerView recyclerView;
    NewRecyclerViewAdapter newRecyclerViewAdapter;
    boolean checkBoxClicked;
    Button addButton;
    int launchActivityCode;
    String itemName;
    Double totalValue;
    Context context;

    TextView textView;
    static ViewGroup layout;

    public class Helper {
        Context mContext;

        Helper(Context ctx) {
            this.mContext = ctx;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recycler_view);
        textView = (TextView) findViewById(R.id.TotalC02KG);
        totalValue = 0.0;

        Intent intent = getIntent();
        Class[] productLine = (Class[]) getIntent().getSerializableExtra("productLine");
        copyArrayList(productLine);
        addButton = (Button) findViewById(R.id.refresh_Button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivityCode = 1;
                Intent intentAddNewItem = new Intent(NewRecyclerView.this, SearchableActivity.class);
                intentAddNewItem.putExtra("productLine", itemShowClassArrayList);
                startActivityForResult(intentAddNewItem,launchActivityCode);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        newRecyclerViewAdapter = new NewRecyclerViewAdapter(itemShowClassArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newRecyclerViewAdapter);
        calculateTotalCO2KG();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == launchActivityCode) {
            if(resultCode == Activity.RESULT_OK){
                DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
                dataBaseHelper.openDataBase();
                 itemName = data.getStringExtra("result");
                 Class newItemObject = new Class();
                 newItemObject.PRODUCT_NAME_DE[0] = itemName;
                 newItemObject.PRODUCT_QUANTITY[0] = dataBaseHelper.getQuantity(dataBaseHelper.getProdcut_IDForSpinner(itemName));
                 newItemObject.Total_kg_CO2_eq_kg[0] = dataBaseHelper.getCO2Value(dataBaseHelper.getProdcut_IDForSpinner(itemName));
                 double n = newItemObject.Total_kg_CO2_eq_kg[0] * (newItemObject.PRODUCT_QUANTITY[0] / 1000);
                 newItemObject.endResult[0] = Math.round(n*1000.0)/1000.0;
                 itemShowClassArrayList.add(newItemObject);
                 calculateTotalCO2KG();

                 newRecyclerViewAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
    }


    public List<Class> copyArrayList(Class[] productLine){
        //itemShowClassArrayList = (ArrayList<Class>) Arrays.asList(productLine);
        itemShowClassArrayList = new ArrayList<>(Arrays.asList(productLine));
        itemShowClassArrayList.removeIf(s->s.PRODUCT_NAME_DE[0].equals("null"));
        return itemShowClassArrayList;
    }

    public void calculateTotalCO2KG(){
        totalValue = 0.0;
        for(int i = 0; i < itemShowClassArrayList.size(); i++){

            totalValue = totalValue + itemShowClassArrayList.get(i).endResult[0];
        }
        textView.setText("Your total CO2KG equals: " + totalValue);
    }


}