package com.example.co2mygroceries;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.File;


public class TextRecognizer extends AppCompatActivity {
    String bitmapToText;
    Bitmap finalBitmap;
    InputImage imageOCR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_recognizer);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bitmapToText = bundle.getString("pathForPhoto");

        File imgFile = new File(bitmapToText);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            finalBitmap = myBitmap;
        }


        TextRecognizer recognizer = (TextRecognizer) TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        InputImage image = InputImage.fromBitmap(finalBitmap, 0);

        Task<Text> result =
                recognizer.p(image)
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @Override
                            public void onSuccess(Text visionText) {
                                // Task completed successfully
                                // ...
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });

    }


    }
    /*public InputImage getImageforOCR(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bitmapToText = bundle.getString("pathForPhoto");

        File imgFile = new File(bitmapToText);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            finalBitmap = myBitmap;
        }


        TextRecognizer recognizer = (TextRecognizer) TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        InputImage image = InputImage.fromBitmap(finalBitmap, 0);
        return image;
    }*/


