package com.example.co2mygroceries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.co2mygroceries.data.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;


public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> implements AdapterView.OnItemSelectedListener {
    private ArrayList<Class> itemShowClassArrayList;
    public Context context;
    public Spinner spinner;

    public recyclerAdapter(ArrayList<Class> itemShowClassArrayList){
        this.itemShowClassArrayList = itemShowClassArrayList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedProduct = parent.getItemAtPosition(position).toString();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewQuantity;
        private TextView textViewCO2;

        public MyViewHolder(final View view){
            super(view);
            textViewCO2 = view.findViewById(R.id.cell_name);
            textViewQuantity = view.findViewById(R.id.cell_co2);
            spinner = view.findViewById(R.id.spinner);

        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        context = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String[] item = itemShowClassArrayList.get(position).PRODUCT_NAME_DE;
        Double[] quanitiy = itemShowClassArrayList.get(position).PRODUCT_QUANTITY;
        Double[] endResult = itemShowClassArrayList.get(position).endResult;
        String[] allProducts = itemShowClassArrayList.get(position).all_Products;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, allProducts);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        holder.textViewCO2.setText(quanitiy[0] + "g");
        holder.textViewQuantity.setText(endResult[0] + "kg");
    }

    @Override
    public int getItemCount() {
        return itemShowClassArrayList.size();
    }
}
