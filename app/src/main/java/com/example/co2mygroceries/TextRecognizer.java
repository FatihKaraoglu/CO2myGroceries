package com.example.co2mygroceries;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
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
import java.util.List;


public class TextRecognizer extends AppCompatActivity {
    String bitmapToText;
    Bitmap finalBitmap, bitmap, myBitmap;
    TextView textToShow;
    String recognizedText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.text_recognizer);

        Log.i("hey", "im here");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bitmapToText = bundle.getString("pathForPhoto");
        Log.i("Test", bitmapToText);
        /*bitmap = (Bitmap) intent.getParcelableExtra("Bitmap");*/

        //File imgFile = (File) intent.getSerializableExtra("pathForPhoto");
        File image = new File(bitmapToText);
        if (image.exists()) {
            while (myBitmap == null) {
                myBitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
            }
            Log.i("HIIII", "exists");
            finalBitmap = myBitmap;
            if(finalBitmap != null){
                Log.i("HIIIII", "Image File exists");
            }
        } else {
            Log.i("HIIII", "Image File is null");
        }
        myAsynctask refrence;
        refrence = new myAsynctask();

        refrence.execute();


        //recognizedText = new myAsynctask().startTextRecognition();
        textToShow = (TextView) findViewById(R.id.recognizedText);
        textToShow.setText(recognizedText);


        /*recognizedText = new myAsynctask().startTextRecognition();
        textToShow = (TextView) findViewById(R.id.recognizedText);
        textToShow.setText(recognizedText);*/
    }
    public Bitmap getFinalBitmap(){
        return myBitmap;
    }



 class myAsynctask extends AsyncTask<Void, Void, Void> {


    Bitmap firebaseBitmap;
    FirebaseVisionImage image;
    String resultText;


    @Override
    protected Void doInBackground(Void... params) {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                TextRecognizer obj = new TextRecognizer();
                firebaseBitmap = obj.getFinalBitmap();
                extractText(startTextRecognition());
            }
        });


        return null;
    }

    public Task<FirebaseVisionText> startTextRecognition() {

        image = FirebaseVisionImage.fromBitmap(firebaseBitmap);
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
    }

    public String extractText(Task<FirebaseVisionText> result) {
        String resulText = result.getResult().getText();
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


