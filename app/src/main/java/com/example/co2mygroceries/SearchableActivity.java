package com.example.co2mygroceries;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.arthurivanets.adapster.listeners.OnItemClickListener;
import com.example.co2mygroceries.data.DataBaseHelper;

import java.util.ArrayList;

public class SearchableActivity extends AppCompatActivity {
    ArrayList<Class> itemShowClassArrayList;
    ArrayAdapter<String> adapter;
    SearchView searchView;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchableactivity);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.openDataBase();


        listView = findViewById(R.id.listView);
        itemShowClassArrayList = new ArrayList<Class>();
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        itemShowClassArrayList = (ArrayList<Class>) getIntent().getSerializableExtra("productLine");
        if(itemShowClassArrayList.size() == 0){
            String[] allNames = dataBaseHelper.getAllNames();
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, allNames);
            listView.setAdapter(adapter);
        } else {
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemShowClassArrayList.get(0).all_Products);
            listView.setAdapter(adapter);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = listView.getItemAtPosition(position);
                String itemName = (listView.getItemAtPosition(position).toString());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", itemName);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
        /*
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);*/
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("search for Product");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}

