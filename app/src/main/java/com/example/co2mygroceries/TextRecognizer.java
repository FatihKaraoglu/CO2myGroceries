package com.example.co2mygroceries;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class TextRecognizer extends AppCompatActivity {
    String bitmapToText;
    Bitmap finalBitmap, myBitmap;
    TextView textToShow;
    String recognizedText;
    float rotation = 90;
    Uri fileUri;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.text_recognizer);


        Log.i("hey", "im here");

        context = getApplicationContext();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bitmapToText = bundle.getString("pathForPhoto");
        fileUri = Uri.fromFile(new File(bitmapToText));
        Log.i("Test", bitmapToText);


        myAsynctask refrence;
        refrence = new myAsynctask();
        refrence.execute(fileUri, context);
    }



    class myAsynctask extends AsyncTask<Object, Void, Void> {
        Uri scanUri;
        Context context;
        FirebaseVisionImage image;
        String resultText;
        Task<FirebaseVisionText> resultTextRecognition;


        @Override
        protected Void doInBackground(Object... params) {
            scanUri = (Uri) params[0];
            context = (Context) params[1];
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    TextRecognizer obj = new TextRecognizer();
                    while (resultTextRecognition != null) {
                        resultTextRecognition = startTextRecognition(scanUri);
                        //extractText(startTextRecognition(firebaseBitmap));
                    }
                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            String test = extractText(resultTextRecognition);

        }

        public Task<FirebaseVisionText> startTextRecognition(Uri scanUri) {


            FirebaseVisionImage image = null;
            try {
                image = FirebaseVisionImage.fromFilePath(context, scanUri);

                FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                        .getOnDeviceTextRecognizer();

                Task<FirebaseVisionText> result =
                        detector.processImage(image)
                                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                                    @Override
                                    public void onSuccess(@NonNull FirebaseVisionText firebaseVisionText) {
                                        // Task completed successfully
                                        // ...
                                        Log.i("MSG", "Task complete");


                                    }
                                })
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Task failed with an exception
                                                // ...
                                                Log.i("MSG", "Task failed");
                                            }
                                        });
                return result;
            } catch (IOException e) {
                Log.i("MSG", "HEYYYYYSY");
            }
            //image = FirebaseVisionImage.fromBitmap(firebaseBitmap);


            return null;
        }

        public String extractText(Task<FirebaseVisionText> result) {
            String resultText = result.getResult().getText();
            for (FirebaseVisionText.TextBlock block : result.getResult().getTextBlocks()) {
                String blockText = block.getText();
                Float blockConfidence = block.getConfidence();
                List<RecognizedLanguage> blockLanguages = block.getRecognizedLanguages();
                Point[] blockCornerPoints = block.getCornerPoints();
                Rect blockFrame = block.getBoundingBox();
                for (FirebaseVisionText.Line line : block.getLines()) {
                    String lineText = line.getText();
                    Float lineConfidence = line.getConfidence();
                    List<RecognizedLanguage> lineLanguages = line.getRecognizedLanguages();
                    Point[] lineCornerPoints = line.getCornerPoints();
                    Rect lineFrame = line.getBoundingBox();
                    for (FirebaseVisionText.Element element : line.getElements()) {
                        String elementText = element.getText();
                        Float elementConfidence = element.getConfidence();
                        List<RecognizedLanguage> elementLanguages = element.getRecognizedLanguages();
                        Point[] elementCornerPoints = element.getCornerPoints();
                        Rect elementFrame = element.getBoundingBox();


                        return resultText;
                    }
                }
            }
            return resultText;
        }
    }
}


