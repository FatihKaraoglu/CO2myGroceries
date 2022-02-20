package com.example.co2mygroceries;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

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
import java.io.IOException;


public class TextRecognizer extends AppCompatActivity {
    String bitmapToText, results;
    Bitmap bitmap;
    float rotation = 90;
    Uri fileUri;
    Context context;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_recognizer);


        context = getApplicationContext();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        bitmapToText = (String) bundle.get("pathForPhoto");
        fileUri = Uri.parse(bitmapToText);
        /*File f = new File(bitmapToText);
        fileUri = Uri.fromFile(f);
        try {
            //bitmap = getBitmap(fileUri);
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //InputImage image = InputImage.fromBitmap(bitmap, 90);
        InputImage image = null;
        try {
            image = InputImage.fromFilePath(this,fileUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        startTextRecognition(image);
    }



    public void startTextRecognition(InputImage image) {
        Intent i = new Intent(this, Results.class);
        com.google.mlkit.vision.text.TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
                recognizer.process(image)
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @Override
                            public void onSuccess(Text result) {
                                String resultText = result.getText();
                                for (Text.TextBlock block : result.getTextBlocks()) {
                                    String blockText = block.getText();
                                    Point[] blockCornerPoints = block.getCornerPoints();
                                    Rect blockFrame = block.getBoundingBox();
                                    for (Text.Line line : block.getLines()) {
                                        String lineText = line.getText();
                                        Point[] lineCornerPoints = line.getCornerPoints();
                                        Rect lineFrame = line.getBoundingBox();
                                        for (Text.Element element : line.getElements()) {
                                            String elementText = element.getText();
                                            Point[] elementCornerPoints = element.getCornerPoints();
                                            Rect elementFrame = element.getBoundingBox();
                                            if(resultText == null){
                                                Log.i("MSG", "Result is null");
                                            } else {
                                                i.putExtra("result", resultText);
                                                startActivity(i);
                                            }
                                        }
                                    }
                                }
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.i("MSG", "Failed");
                                        // Task failed with an exception
                                        // ...
                                    }
                                });

        }
    public Bitmap getBitmap(Uri fileUri) throws IOException {
        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
        return bitmap;
    }
}


