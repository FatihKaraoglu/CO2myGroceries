package com.example.co2mygroceries.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 2;
    public Context context;
    static SQLiteDatabase sqLiteDatabase;
    static String ProductId = "PRODUKT_ID";
    static int ANZAHL_PRODUKTE = 414;

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }



    public void createDatabase() throws IOException{
        boolean databaseExists = checkDataBase();

        if (databaseExists){
            Log.i("MSG", "Database file exists");

        }else{
            this.getWritableDatabase();
            copyDataBase();
        }
    }

    public boolean checkDataBase(){
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }


    private void copyDataBase() throws IOException{
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = context.getDatabasePath(DATABASE_NAME).toString();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void readProduct(){
        String query ="select PRODUCT_NAME from PRODUCT_INFO";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        Log.i("MSG50", cursor.getString(0));
    }


    public void openDataBase() throws SQLException {
        String myPath = context.getDatabasePath(DATABASE_NAME).toString();
        Log.i("MSG",myPath);
        sqLiteDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public void writeProductInfo(String productName, String productSize) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PRODUCTNAME", productName);
        values.put("PRODUCTSIZE", productSize);
        db.insert("INFO", null, values);
    }
    @Override
    public synchronized void close() {
        if(sqLiteDatabase != null)
            sqLiteDatabase.close();
        super.close();
    }

    public String[] getProductName(){
        String query = "select NAME__DEUTSCH_ from DATENBANK";
        Log.i("MSG", query);
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String [] productName = new String[ANZAHL_PRODUKTE];
        cursor.moveToFirst();
        int i = 0;
        while (i < ANZAHL_PRODUKTE){
            productName[i] = cursor.getString(0);
            cursor.moveToNext();
            Log.i("MSG", productName[i]);
            i++;

        }
        return productName;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}