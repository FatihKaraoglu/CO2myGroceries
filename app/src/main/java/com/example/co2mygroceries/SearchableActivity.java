package com.example.co2mygroceries;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchableActivity extends ListActivity {
    ArrayList<Class> itemShowClassArrayList;
    ArrayAdapter<String> adapter;
    SearchView searchView;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchableactivity);
        listView = (ListView) findViewById(R.);
        searchView = (SearchView) findViewById(R.id.searchView);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        itemShowClassArrayList = (ArrayList<Class>) getIntent().getSerializableExtra("productLine");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemShowClassArrayList.get(0).all_Products);
        listView.setAdapter(adapter);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    public void doMySearch(String query){


    }

}
