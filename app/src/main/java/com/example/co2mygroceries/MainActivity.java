package com.example.co2mygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.co2mygroceries.data.DataBaseHelper;
import com.google.android.material.navigation.NavigationView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.debatty.java.stringsimilarity.JaroWinkler;

public class MainActivity extends AppCompatActivity {
TextView scanbtn;
Button OCRbutton;
Uri cropImageUri;
String text = "Select a image to scan!";
int time = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        OCRbutton = (Button) findViewById(R.id.OCR);
        scanbtn = (TextView) findViewById(R.id.scanbutton);
        scanbtn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageCropper();
            }
        });

        scanbtn = (TextView) findViewById(R.id.scanbutton);

        OCRbutton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cropImageUri == null){
                    showToastMessage();
                }else{
                    startOCR();
                }
            }
        });


    }

    public void openscanActivity(){
        Intent intent = new Intent(this, Scan.class);
        startActivity(intent);
    }

    public void openImageCropper(){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                ImageView imageView = findViewById(R.id.cropImageView);
                imageView.setImageURI(resultUri);
                cropImageUri = resultUri;
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    public void startOCR(){

        Intent startOCR = new Intent(this, TextRecognizer.class);
        startOCR.putExtra("pathForPhoto", cropImageUri.toString());
        startActivity(startOCR);
    }

    public void showToastMessage(){
        Toast.makeText(this, text,Toast.LENGTH_LONG).show();
    }

    public void menu(){

    }
}
