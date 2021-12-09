package com.example.co2mygroceries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.co2mygroceries.Scan;

import java.io.File;

public class Show_Pictures extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);
        String path = getIntent().getStringExtra("currentPhotoPath");

        File imgFile = new  File(path);
        if(imgFile.exists())
        {
            ImageView myImage = new ImageView(this);
            myImage.setImageURI(Uri.fromFile(imgFile));

        }


    }
}
