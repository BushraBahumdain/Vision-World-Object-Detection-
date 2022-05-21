package com.example.visionworldopencv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.visionworldopencv.databinding.ActivityMainBinding;

import org.opencv.android.OpenCVLoader;

import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static TextToSpeech ttsM;

    static {
        if(OpenCVLoader.initDebug()){
            Log.d("MainActivity", "OpenCV is loaded");
        }else{
            Log.d("MainActivity", "OpenCV is not loaded");
        }


    }

    ActivityMainBinding binding;

    private Button Camera_Button;
//    private objectDetectionClass objectDetectionClass;
    //public String prevStarted = "yes";


    /*protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        } else {
            moveToSecondary();
        }
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ttsM = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR)
                    ttsM.setLanguage(Locale.US);
                 ttsM.speak("Click on the get object detection button in the middle of the screen", TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        //ttsM.speak("Click on the get objct detection button in the middle ", TextToSpeech.QUEUE_FLUSH, null);



        /*try {
            objectDetectionClass = new objectDetectionClass(getAssets(), "ssd_mobilenet_v1_1_metadata_1.tflite","labelmap.txt", 300);
            Log.d("MainActivity", "Model is successfully loaded");

        }catch (IOException e){
            Log.d("MainActivity","Getting some error");
            e.printStackTrace();
        }*/

        Camera_Button= findViewById(R.id.ObjectDetectionButton);
        Camera_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CameraActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));

                }
        });
    }

    /*public void moveToSecondary(){
        // use an intent to travel from one activity to another.
        Intent intent = new Intent(this,WelcomePage.class);
        startActivity(intent);
    }*/
}