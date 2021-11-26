package com.example.co2mygroceries.data;


import com.example.co2mygroceries.data.FoodContracts;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ProductDbHelper extends SQLiteOpenHelper {
     public static final Integer DATABASE_VERSION = 1;
     public static final String DATABASE_NAME = "Product.db";
     //Constructor
     public ProductDbHelper (Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
     }
         @Override
         public void onCreate (SQLiteDatabase db){
             String SQL_CREATE_ENTRIES = "CREATE TABLE " + DATABASE_NAME + "(" +
                     FoodContracts.PRODUCT_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                     FoodContracts.PRODUCT_CATEGORY + "TEXT," +
                     FoodContracts.PRODUCT_NAME + "TEXT NOT NULL," +
                     FoodContracts.PRODUCT_UNIT + "TEXT NOT NULL," +
                     FoodContracts.PRODUCT_CO2E + "INTEGER NOT NULL, " +
                     FoodContracts.PRODUCT_AGRICULTURE + " INTEGER," +
                     FoodContracts.PRODUCT_iLUC + " INTEGER, " +
                     FoodContracts.PRODUCT_FOODPROCESSING + " INTEGER ," +
                     FoodContracts.PRODUCT_PACKAGING + " INTEGER, " +
                     FoodContracts.PRODUCT_TRANSPORT + " INTEGER, " +
                     FoodContracts.PRODUCT_RETAIL + " INTEGER);";
             db.execSQL(SQL_CREATE_ENTRIES);
         }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           /* String SQL_DELETE_ENTRIES =
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);*/
        }
         @Override
         public void onDowngrade (SQLiteDatabase db,int oldVersion, int newVersion){
             onUpgrade(db, oldVersion, newVersion);
         }
     }
