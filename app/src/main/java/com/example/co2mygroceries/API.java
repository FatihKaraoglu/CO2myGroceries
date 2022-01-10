package com.example.co2mygroceries;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class API extends AppCompatActivity {

    public void buildTable(Context context){
        String searchWord =  "pizza";

        String url = "https://de.openfoodfacts.org/cgi/search.pl?search_terms="+searchWord+"&_simple=1&action=process&page=1&page_size=1000&json=true";

        String[] productnameArray;
        String[] productSize;

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest response = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int page;
                int pageCount;
                int productCount;
                try {
                    productCount = response.getInt("count");
                    page = response.getInt("page");
                    pageCount = response.getInt("page_count");
                    JSONArray array;
                    array = response.getJSONArray("products");
                    for (int i = 0 ; i <= pageCount; i++ ) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        String productName = jsonObject.getString("product_name");
                        String productSize = jsonObject.getString("quantity");
                        Log.i("MSG", productName + productSize);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(response);


    }


    public void buildUrl(){

    }


}