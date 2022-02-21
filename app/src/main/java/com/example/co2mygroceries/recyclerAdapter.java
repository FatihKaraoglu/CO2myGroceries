package com.example.co2mygroceries;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    int positionS;

    String mySpinner_selectedId;


    public recyclerAdapter(ArrayList<Class> itemShowClassArrayList){
        this.itemShowClassArrayList = itemShowClassArrayList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id ) {

        MyViewHolder myViewHolder = new MyViewHolder(view);
        itemShowClassArrayList.get(positionS).PRODUCT_QUANTITY[0] = 999.0;
        //this.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public double calculateC02Spinner(double CO2Value, double PRODUCT_QUANTITY){
        double n = CO2Value * (PRODUCT_QUANTITY / 1000);
        double endResult = Math.round(n*1000.0)/1000.0;
        return endResult;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewQuantity;
        private TextView textViewCO2;
        public Spinner spinner;
        int positionSpinner;
        int count;
        Button refreshButton;
        ArrayAdapter<String> arrayAdapter;


        public MyViewHolder(final View view){
            super(view);
            textViewCO2 = view.findViewById(R.id.cell_name);
            textViewQuantity = view.findViewById(R.id.cell_co2);
            spinner = view.findViewById(R.id.spinner);
            refreshButton = view.findViewById(R.id.refresh_Button);


        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        context = parent.getContext();
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        myViewHolder.spinner.setOnItemSelectedListener(this);

        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String[] item = itemShowClassArrayList.get(position).PRODUCT_NAME_DE;
        Double[] quantity = itemShowClassArrayList.get(position).PRODUCT_QUANTITY;
        Double[] endResult = itemShowClassArrayList.get(position).endResult;

        holder.itemView.setTag(position);

        holder.arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, itemShowClassArrayList.get(position).all_Products);
        holder.spinner.setAdapter(holder.arrayAdapter);
        holder.spinner.setPrompt("Choose the right product");
        holder.spinner.setSelection(0,false);

        //holder.spinner.setOnItemSelectedListener(this);
        Log.i("YUUUUUUUAAAAAHHHHHIIIIIII: ", holder.arrayAdapter.toString());

        selectValue(holder.spinner, item[0]);
        holder.textViewCO2.setText(quantity[0] + "g");
        holder.textViewQuantity.setText(endResult[0] + "kg");

    }



    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i,false);
                break;
            }
        }
    }

        @Override
        public int getItemCount() {
            return itemShowClassArrayList.size();
    }
}

