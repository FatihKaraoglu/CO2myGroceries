package com.example.co2mygroceries;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.co2mygroceries.Scan;

import java.io.File;

public class Show_Pictures extends AppCompatActivity {
String pathForPhoto;
TextView UserQuestion;
Button UserQuestionYes,UserQuestionNo;
Bitmap bitmapToText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);

        setImageView();
        UserQuestionYes = (Button) findViewById(R.id.UserQuestionYes);
        UserQuestionYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTakeAnotherPicture();
            }
        });
        UserQuestionNo = (Button) findViewById(R.id.UserQuestionNo);
        UserQuestionNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOCR();
            }
        });

    }
        public void setImageView(){
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            pathForPhoto = bundle.getString("photopath");

            File imgFile = new File(pathForPhoto);
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ImageView myImage = (ImageView) findViewById(R.id.imageView);

                myImage.setImageBitmap(myBitmap);
                bitmapToText = myBitmap;
            }
        }
        public void setTakeAnotherPicture(){
            Intent TakeAnotherPicture = new Intent(this, Scan.class);
            startActivity(TakeAnotherPicture);

        }

        public void startOCR(){
            Log.i("MSG", "OCR has started");
            Intent startOCR = new Intent(this, TextRecognizer.class);
            startOCR.putExtra("pathForPhoto", pathForPhoto);
            startActivity(startOCR);
        }
}
