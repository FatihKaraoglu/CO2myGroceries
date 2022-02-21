package com.example.co2mygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecylerP extends AppCompatActivity {
    recyclerAdapter adapter;
    private ArrayList<Class> itemShowClassArrayList;
    private RecyclerView recyclerView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_p);
        recyclerView = findViewById(R.id.recycler_view);

        Intent intent = getIntent();
        Class[] productLine = (Class[]) getIntent().getSerializableExtra("productLine");
        context = getApplicationContext();

        copyArrayList(productLine);
        setAdapter();

    }

    public void setAdapter() {
        adapter = new recyclerAdapter(itemShowClassArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    public List<Class> copyArrayList(Class[] productLine){
        //itemShowClassArrayList = (ArrayList<Class>) Arrays.asList(productLine);
        itemShowClassArrayList = new ArrayList<>(Arrays.asList(productLine));
        return itemShowClassArrayList;
    }


}