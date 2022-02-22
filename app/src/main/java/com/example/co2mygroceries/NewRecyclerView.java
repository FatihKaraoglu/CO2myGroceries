package com.example.co2mygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewRecyclerView extends AppCompatActivity {
    ArrayList<Class> itemShowClassArrayList;
    RecyclerView recyclerView;
    NewRecyclerViewAdapter newRecyclerViewAdapter;
    boolean checkBoxClicked;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recycler_view);


        Intent intent = getIntent();
        Class[] productLine = (Class[]) getIntent().getSerializableExtra("productLine");
        copyArrayList(productLine);
        addButton = (Button) findViewById(R.id.refresh_Button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddNewItem = new Intent(NewRecyclerView.this, SearchableActivity.class);
                intentAddNewItem.putExtra("productLine", itemShowClassArrayList);
                startActivity(intentAddNewItem);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        newRecyclerViewAdapter = new NewRecyclerViewAdapter(itemShowClassArrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(newRecyclerViewAdapter);

    }


    public List<Class> copyArrayList(Class[] productLine){
        //itemShowClassArrayList = (ArrayList<Class>) Arrays.asList(productLine);
        itemShowClassArrayList = new ArrayList<>(Arrays.asList(productLine));
        return itemShowClassArrayList;
    }
}