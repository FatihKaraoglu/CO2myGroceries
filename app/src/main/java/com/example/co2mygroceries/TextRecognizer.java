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
    String bitmapToText;
    Bitmap bitmap;
    TextView textToShow;
    String recognizedText;
    float rotation = 90;
    Uri fileUri;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.text_recognizer);



        context = getApplicationContext();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        bitmapToText = bundle.getString("pathForPhoto");
        File f = new File(bitmapToText);
        fileUri = Uri.fromFile(f);
        try {
            bitmap = getBitmap(fileUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputImage image = InputImage.fromBitmap(bitmap, 90);
        myAsynctask refrence;
        refrence = new myAsynctask();
        refrence.execute(image);
    }
    public Bitmap getBitmap(Uri fileUri) throws IOException{
        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
        return bitmap;
    }



    class myAsynctask extends AsyncTask<Object, Void, String> {
        Uri scanUri;
        Bitmap scanBitmap;
        Context context;
        InputImage image;
        String resultString;
        Task<Text> resultTextRecognition;


        @Override
        protected String doInBackground(Object... params) {
            image = (InputImage) params[0];

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    TextRecognizer obj = new TextRecognizer();
                    while (resultTextRecognition != null) {
                        resultString = startTextRecognition(image);
                        //extractText(startTextRecognition(firebaseBitmap));
                    }
                }
            });


            return resultString;
        }


        protected void onPostExecute(String string) {
            String test = resultString;
            Log.i("MSG", test);

        }

        public String startTextRecognition(InputImage image) {
            com.google.mlkit.vision.text.TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
            Task<Text> result =
                    recognizer.process(image)
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

            String resultText = result.getResult().getText();
            for (Text.TextBlock block : result.getResult().getTextBlocks()) {
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
                    }
                }
            }
            Log.i("MSG", resultText);
            return resultText;
        }
    }
}


