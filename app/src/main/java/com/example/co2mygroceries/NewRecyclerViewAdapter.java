package com.example.co2mygroceries;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewRecyclerViewAdapter extends RecyclerView.Adapter<NewRecyclerViewAdapter.ViewHolder> {
    View view;
    ArrayList<Class> classArrayList;
    NewRecyclerView newRecyclerView;
    double totalValue;



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textviewName, textViewQuantity, textViewCo2, textViewTotal;
        EditText editText;
        CheckBox checkBox;

        public ViewHolder(View itemView){
            super(itemView);
            view = itemView;

            textviewName = (TextView) itemView.findViewById(R.id.textViewProductName);
            textViewQuantity = (TextView) itemView.findViewById(R.id.textViewQuantity);
            textViewCo2 = (TextView) itemView.findViewById(R.id.textViewCO2);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);

            newRecyclerView = new NewRecyclerView();

        }

    }

    public NewRecyclerViewAdapter(ArrayList<Class> itemShowClassArrayList){
        classArrayList = itemShowClassArrayList;
    }


    @NonNull
    @Override
    public NewRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.row_item, parent, false);




        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewRecyclerViewAdapter.ViewHolder holder, int position) {
        Class myClass = classArrayList.get(position);



        holder.textviewName.setText(myClass.PRODUCT_NAME_DE[0]);
        holder.textViewQuantity.setText(myClass.PRODUCT_QUANTITY[0] +"g");
        holder.textViewCo2.setText(myClass.endResult[0] + "kg");
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classArrayList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return classArrayList.size();
    }
}
