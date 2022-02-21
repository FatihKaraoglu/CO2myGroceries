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

    String mySpinner_selectedId;


    public recyclerAdapter(ArrayList<Class> itemShowClassArrayList){
        this.itemShowClassArrayList = itemShowClassArrayList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id ) {
        RecylerP recylerP = new RecylerP();
        MyViewHolder myViewHolder = new MyViewHolder(view);
        itemShowClassArrayList.get(myViewHolder.positionSpinner).PRODUCT_QUANTITY[0] = 999.0;
        notifyDataSetChanged();

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
        ArrayAdapter<String> arrayAdapter;

        public MyViewHolder(final View view){
            super(view);

            textViewCO2 = view.findViewById(R.id.cell_name);
            textViewQuantity = view.findViewById(R.id.cell_co2);
            spinner = view.findViewById(R.id.spinner);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positionSpinner = getAdapterPosition();
                }
            });
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

        holder.arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, itemShowClassArrayList.get(position).all_Products);
        holder.spinner.setAdapter(holder.arrayAdapter);
        holder.spinner.setPrompt("Choose the right product");
        holder.spinner.setOnItemSelectedListener(this);


        selectValue(holder.spinner, item[0]);
        holder.textViewCO2.setText(quanitiy[0] + "g");
        holder.textViewQuantity.setText(endResult[0] + "kg");

    }

    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemShowClassArrayList.size();
    }
}
